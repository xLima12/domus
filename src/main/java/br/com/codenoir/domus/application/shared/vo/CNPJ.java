package br.com.codenoir.domus.application.shared.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class CNPJ {

    @NotBlank(message = "CNPJ cannot blank")
    @NotNull(message = "CNPJ cannot null")
    @Column(name = "cnpj")
    @Size(min = 14, max = 14, message = "CNPJ must have exactly 14 numeric digits")
    private String value;

    public CNPJ(String value) {
        this.value = value;
    }

}
