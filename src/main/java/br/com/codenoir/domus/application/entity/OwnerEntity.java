package br.com.codenoir.domus.application.entity;

import br.com.codenoir.domus.application.vo.CPF;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "tb_owner")
@Data
public class OwnerEntity extends UserEntity {

    private String ownerType;
    @Embedded
    private CPF cpf;

}
