package br.com.codenoir.domus.application.auth.service;

import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.shared.enums.Roles;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoderService passwordEncoderService;

    public <T>AuthResponseDTO authenticate(
        String secretKey,
        Optional<T> entity,
        Function<T, String> idExtractor,
        Function<T, String> passwordGetter,
        Function<T, List<Roles>> rolesExtractor,
        String requestPassword
    ) {
        var authenticatable = entity.orElseThrow(() -> new IllegalArgumentException("Username/password incorrect"));

        var passwordMatches = passwordEncoderService.matches(requestPassword, passwordGetter.apply(authenticatable));

        if(!passwordMatches) {
            throw new IllegalArgumentException("Username/password incorrect");
        }

        List<Roles> roles = rolesExtractor.apply(authenticatable);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        String issuer = "domus";

        var token = JWT.create()
            .withIssuer(issuer)
            .withExpiresAt(expiresIn)
            .withSubject(idExtractor.apply(authenticatable))
            .withClaim("roles", roles.stream().map(Roles::name).collect(Collectors.toList()))
            .sign(algorithm);

        return new AuthResponseDTO(token, expiresIn.toString(), roles);
    }

}
