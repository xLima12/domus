package br.com.codenoir.domus.application.shared.entity;

import br.com.codenoir.domus.application.shared.vo.EmailAddress;
import br.com.codenoir.domus.application.shared.vo.Password;
import br.com.codenoir.domus.application.shared.vo.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @NotNull(message = "First name cannot null")
    private String firstName;

    @NotNull(message = "Last name cannot null")
    private String lastName;

    @Embedded
    private EmailAddress emailAddress;

}
