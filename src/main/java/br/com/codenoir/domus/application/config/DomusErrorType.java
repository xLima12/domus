package br.com.codenoir.domus.application.config;

import graphql.ErrorClassification;

public enum DomusErrorType implements ErrorClassification {

    UNAUTHORIZED,
    FORBIDDEN,
    BAD_REQUEST,
    INTERNAL_SERVER_ERROR,
    NOT_FOUND;

    @Override
    public String toString() {
        return name();
    }
}
