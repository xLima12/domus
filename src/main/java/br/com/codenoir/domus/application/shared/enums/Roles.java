package br.com.codenoir.domus.application.shared.enums;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public enum Roles {

    ADMIN(0),
    SURVEYOR(1),
    COMPANY(2);

    private final int role;

    Roles(int role) {
        this.role = role;
    }

}
