package br.com.codenoir.domus.application.employee.service;

import br.com.codenoir.domus.application.auth.dto.AuthRequestDTO;
import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.employee.repository.EmployeeRepository;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.shared.vo.Username;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthEmployeeService {

    @Value("${security.token.secret.employee}")
    private String secretKey;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoderService passwordEncoder;

    public AuthResponseDTO signIn(AuthRequestDTO authRequestDTO) {
        var employee = employeeRepository.findByUsername(new Username(authRequestDTO.getUsername().getValue()))
            .orElseThrow(() -> new IllegalArgumentException("Username/password incorrect"));

        var passwordMatches = passwordEncoder.matches(authRequestDTO.getPassword().getValue(),
            employee.getPassword().getValue());

        if(!passwordMatches) {
            throw new IllegalArgumentException("Username/password incorrect");
        }

        var roles = employee.getRoles().toString();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        String issuer = "domus";

        var token = JWT.create()
            .withIssuer(issuer)
            .withExpiresAt(expiresIn)
            .withSubject(employee.getId().toString())
            .withClaim("roles", List.of(roles))
            .sign(algorithm);

        return new AuthResponseDTO(token, expiresIn.toString(), roles);
    }

}
