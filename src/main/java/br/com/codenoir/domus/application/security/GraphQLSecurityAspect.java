package br.com.codenoir.domus.application.security;

import br.com.codenoir.domus.application.exception.ForbiddenException;
import br.com.codenoir.domus.application.exception.UnauthorizedException;
import graphql.schema.DataFetchingEnvironment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@Aspect
@Component
public class GraphQLSecurityAspect {
    @Around("@annotation(br.com.codenoir.domus.application.security.DomusRolesAllowed)")
    public Object checkSecurityAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        DomusRolesAllowed annotation = method.getAnnotation(DomusRolesAllowed.class);

        Object[] args = joinPoint.getArgs();
        DataFetchingEnvironment environment = null;

        for(Object arg : args) {
            if(arg instanceof DataFetchingEnvironment) {
                environment = (DataFetchingEnvironment) arg;
                break;
            }
        }

        if(environment == null) {
            Parameter[] parameters = method.getParameters();
            for(int i = 0; i < parameters.length; i++) {
                if(DataFetchingEnvironment.class.isAssignableFrom(parameters[i].getType())) {
                    throw new IllegalArgumentException("DataFetchingEnvironment is required for authorization verification.");
                }
            }

            return joinPoint.proceed();
        }

        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");

        if(userContext == null) {
            throw new UnauthorizedException("User not authenticated");
        }

        String[] requiredRoles = annotation.value();
        if(requiredRoles.length > 0) {
            boolean hasAnyRole = Arrays.stream(requiredRoles).anyMatch(userContext::hasRole);

            if(!hasAnyRole) {
                throw new ForbiddenException("User doesn't have required roles");
            }
        }

        return joinPoint.proceed();
    }
}
