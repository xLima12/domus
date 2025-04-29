package br.com.codenoir.domus.application.entity;

import br.com.codenoir.domus.application.vo.CNPJ;
import br.com.codenoir.domus.application.vo.EmailAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity(name = "tb_company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "Name cannot blank")
    @NotNull(message = "Name cannot null")
    private String name;

    private String userName;
    private String password;

    @Embedded
    private CNPJ cnpj;
    @Embedded
    private EmailAddress emailAddress;

}
