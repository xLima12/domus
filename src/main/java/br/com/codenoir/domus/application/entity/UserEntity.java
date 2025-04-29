package br.com.codenoir.domus.application.entity;

import br.com.codenoir.domus.application.vo.EmailAddress;
import br.com.codenoir.domus.application.vo.Password;
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

    private String userName;

    @Embedded
    private Password password;

    @NotBlank(message = "First name cannot blank")
    @NotNull(message = "First name cannot null")
    private String firstName;

    @NotBlank(message = "Last name cannot blank")
    @NotNull(message = "Last name cannot null")
    private String lastName;

    @Embedded
    private EmailAddress emailAddress;

}
