package br.com.codenoir.domus.application.enums;

import lombok.Getter;

@Getter
public enum Roles {

    ADMIN(0),
    SURVEYOR(1);

    private final int role;

    Roles(int role) {
        this.role = role;
    }

}
