package br.com.codenoir.domus.application.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtService {

    @Value("${security.token.secret.employee}")
    private String secretKeyEmployee;

    @Value("${security.token.secret.company}")
    private String secretKeyCompany;

    private Map<String, String> secretKeys;

    @PostConstruct
    private void initSecrets() {
        secretKeys = Map.of(
            "ADMIN", secretKeyEmployee,
            "SURVEYOR", secretKeyEmployee,
            "COMPANY", secretKeyCompany
        );
    }

    private Algorithm getAlgorithmForRole(String role) {
        String secret = secretKeys.get(role);

        if(secret == null) {
            throw new RuntimeException("Role is invalid: " + role);
        }

        return Algorithm.HMAC256(secret);
    }

    public Optional<DomusGraphQLContext> validateToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            List<String> roles = jwt.getClaim("roles").asList(String.class);

            if (roles == null || roles.isEmpty()) {
                return Optional.empty();
            }

            var roleForSecret = roles.get(0);

            if (!secretKeys.containsKey(roleForSecret)) {
                return Optional.empty();
            }

            JWTVerifier verifier = verifier = JWT.require(getAlgorithmForRole(roleForSecret)).build();
            jwt = verifier.verify(token);

            String userId = jwt.getSubject();

            return Optional.of(new DomusGraphQLContext(userId, roles, token));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
