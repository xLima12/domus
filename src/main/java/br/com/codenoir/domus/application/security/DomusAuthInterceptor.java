package br.com.codenoir.domus.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DomusAuthInterceptor implements WebGraphQlInterceptor {

    private final JwtService jwtService;

    @Autowired
    public DomusAuthInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        String authHeader = request.getHeaders().getFirst("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Optional<DomusGraphQLContext> authContext = jwtService.validateToken(token);

            if(authContext.isPresent()) {
                Map<String, Object> contextMap = new HashMap<>();
                contextMap.put("auth", authContext.get());

                request.configureExecutionInput((executionInput, builder) ->
                    builder.graphQLContext(contextMap).build());
            }
        }

        return chain.next(request);
    }

}
