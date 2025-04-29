package br.com.codenoir.domus.application.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class CNPJ {

    @NotBlank(message = "CNPJ cannot blank")
    @NotNull(message = "CNPJ cannot null")
    @org.hibernate.validator.constraints.br.CNPJ(message = "CNPJ is invalid")
    private String value;

    public CNPJ(String value) {
        this.value = value;
    }

}
