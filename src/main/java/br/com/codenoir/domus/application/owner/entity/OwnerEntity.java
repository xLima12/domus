package br.com.codenoir.domus.application.owner.entity;

import br.com.codenoir.domus.application.shared.enums.OwnerType;
import br.com.codenoir.domus.application.shared.vo.CNPJ;
import br.com.codenoir.domus.application.shared.vo.CPF;
import br.com.codenoir.domus.application.shared.vo.EmailAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity(name = "tb_owner")
@Data
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "First name cannot null")
    private String firstName;

    @NotNull(message = "Last name cannot null")
    private String lastName;

    @Embedded
    private EmailAddress emailAddress;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Owner type should not be null")
    private OwnerType ownerType;

    @Embedded
    private CPF cpf;

    @Embedded
    private CNPJ cnpj;

}
