package br.com.codenoir.domus.application.dto;

import br.com.codenoir.domus.application.enums.OwnerType;
import br.com.codenoir.domus.application.vo.*;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class OwnerRequestDTO {

    @Valid
    private Username username;

    @Valid
    private Password password;

    @NotNull(message = "First name cannot null")
    private String firstName;

    @NotNull(message = "Last name cannot null")
    private String lastName;

    @Valid
    private EmailAddress emailAddress;

    @NotNull(message = "Owner type should not be null")
    private Integer ownerType;

    @NotNull(message = "Document should not be null")
    private String document;

}
