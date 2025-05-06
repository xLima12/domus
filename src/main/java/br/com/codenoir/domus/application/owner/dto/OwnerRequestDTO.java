package br.com.codenoir.domus.application.owner.dto;

import br.com.codenoir.domus.application.shared.vo.EmailAddress;
import br.com.codenoir.domus.application.shared.vo.Password;
import br.com.codenoir.domus.application.shared.vo.Username;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
