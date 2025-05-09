package br.com.codenoir.domus.application.config;

import br.com.codenoir.domus.application.exception.*;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class DomusGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    private record ErrorMapping(DomusErrorType errorType, int statusCode) {}

    private static final Map<Class<? extends Throwable>, ErrorMapping> EXCEPTION_MAPPINGS = Map.of(
        UnauthorizedException.class, new ErrorMapping(DomusErrorType.UNAUTHORIZED, 401),
        ForbiddenException.class, new ErrorMapping(DomusErrorType.FORBIDDEN, 403),
        NotFoundException.class, new ErrorMapping(DomusErrorType.NOT_FOUND, 404),
        BadRequestException.class, new ErrorMapping(DomusErrorType.BAD_REQUEST, 400)
    );

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorMapping mapping = mapException(ex);

        String traceId = Optional.ofNullable(MDC.get("traceId")).orElse(UUID.randomUUID().toString());
        MDC.put("traceId", traceId);

        logErrorWithContext(ex, env, mapping, traceId);

        return buildGraphQLError(ex, env, mapping, traceId);
    }

    private ErrorMapping mapException(Throwable ex) {
        return EXCEPTION_MAPPINGS.entrySet().stream()
            .filter(entry -> entry.getKey().isAssignableFrom(ex.getClass()))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(new ErrorMapping(DomusErrorType.INTERNAL_SERVER_ERROR, 500));
    }

    private GraphQLError buildGraphQLError(Throwable ex, DataFetchingEnvironment env, ErrorMapping mapping, String traceId) {
        return GraphqlErrorBuilder.newError(env)
            .message(ex.getMessage())
            .errorType(mapping.errorType)
            .extensions(Map.of(
                "status", mapping.statusCode,
                "error", mapping.errorType.toString(),
                "traceId", traceId
            ))
            .build();
    }

    private void logErrorWithContext(Throwable ex, DataFetchingEnvironment env, ErrorMapping mapping, String traceId) {
        String path = String.join("/", env.getExecutionStepInfo().getPath().toList().stream()
            .map(Object::toString).toList());

        String location = env.getField().getSourceLocation().toString();

        String logMsg = """
            GraphQL Error | type=%s | path=%s | location=%s | traceId=%s | message=%s
            """.formatted(mapping.errorType, path, location, traceId, ex.getMessage());

        if(mapping.statusCode >= 500) {
            log.error(logMsg, ex);
        } else {
            log.warn(logMsg);
        }
    }

}
