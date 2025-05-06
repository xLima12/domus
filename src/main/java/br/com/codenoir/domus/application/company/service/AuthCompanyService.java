package br.com.codenoir.domus.application.company.service;

import br.com.codenoir.domus.application.auth.dto.AuthRequestDTO;
import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.company.repository.CompanyRepository;
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
public class AuthCompanyService {

    @Value("${security.token.secret.company}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BCryptPasswordEncoderService passwordEncoderService;

    public AuthResponseDTO signIn(AuthRequestDTO authRequestDTO) {
        var company = this.companyRepository.findByUsername(new Username(authRequestDTO.getUsername().getValue()))
            .orElseThrow(() -> new IllegalArgumentException("Username/password incorrect"));

        var passwordMatches = passwordEncoderService.matches(authRequestDTO.getPassword().getValue(),
            company.getPassword().getValue());

        if(!passwordMatches) {
            throw new IllegalArgumentException("Username/password incorrect");
        }

        var roles = company.getRoles().toString();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        String issuer = "domus";

        var token = JWT.create()
            .withIssuer(issuer)
            .withExpiresAt(expiresIn)
            .withSubject(company.getId().toString())
            .withClaim("roles", List.of(roles))
            .sign(algorithm);

        return new AuthResponseDTO(token, expiresIn.toString(), roles);
    }

}
