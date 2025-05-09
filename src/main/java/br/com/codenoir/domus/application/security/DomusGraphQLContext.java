package br.com.codenoir.domus.application.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DomusGraphQLContext {

    private final String userId;
    private final List<String> roles;
    private final String token;

    public boolean hasRole(String role) {
        return roles.contains(role);
    }
}
