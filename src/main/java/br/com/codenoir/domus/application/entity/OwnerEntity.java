package br.com.codenoir.domus.application.entity;

import br.com.codenoir.domus.application.enums.OwnerType;
import br.com.codenoir.domus.application.vo.CNPJ;
import br.com.codenoir.domus.application.vo.CPF;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "tb_owner")
@Data
public class OwnerEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Owner type should not be null")
    private OwnerType ownerType;

    @Embedded
    private CPF cpf;

    @Embedded
    private CNPJ cnpj;

}
