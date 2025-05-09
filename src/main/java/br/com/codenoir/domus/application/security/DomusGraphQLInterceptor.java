package br.com.codenoir.domus.application.security;

import br.com.codenoir.domus.application.exception.ForbiddenException;
import br.com.codenoir.domus.application.exception.UnauthorizedException;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class DomusGraphQLInterceptor {

    public <T> DataFetcher<T> secure (DataFetcher<T> originalFetcher, Method method) {
        DomusRolesAllowed secured = method.getAnnotation(DomusRolesAllowed.class);

        if(secured == null) {
            return originalFetcher;
        }

        return environment -> {
            DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");

            if(userContext == null) {
                throw new UnauthorizedException("User not authenticated");
            }

            checkAuthorization(userContext, secured);

            return originalFetcher.get(environment);
        };
    }

    public void checkAuthorization(DomusGraphQLContext userContext, DomusRolesAllowed secured) {
        if(userContext.getUserId() == null) {
            throw new UnauthorizedException("User not authenticated");
        }

        if(secured.value().length > 0) {
            boolean hasAnyRole = Arrays.stream(secured.value()).anyMatch(userContext::hasRole);

            if(!hasAnyRole) {
                throw new ForbiddenException("User doesn't have required roles");
            }
        }
    }
}
