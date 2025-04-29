package br.com.codenoir.domus.application.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class CPF {

    @NotBlank(message = "CPF cannot blank")
    @NotNull(message = "CPF cannot null")
    @org.hibernate.validator.constraints.br.CPF(message = "CPF is invalid")
    @Column(name = "cpf")
    private String value;

    public CPF(String value) {
        this.value = value;
    }

}
