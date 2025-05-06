package br.com.codenoir.domus.application.company.entity;

import br.com.codenoir.domus.application.shared.enums.Roles;
import br.com.codenoir.domus.application.shared.vo.CNPJ;
import br.com.codenoir.domus.application.shared.vo.EmailAddress;
import br.com.codenoir.domus.application.shared.vo.Password;
import br.com.codenoir.domus.application.shared.vo.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity(name = "tb_company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Name cannot null")
    private String name;

    @Embedded
    private Username username;

    @Embedded
    private Password password;

    @Embedded
    private CNPJ cnpj;

    @Embedded
    private EmailAddress emailAddress;

    @Enumerated(EnumType.STRING)
    private List<@NotNull(message = "Role cannot null") Roles> roles;

}
