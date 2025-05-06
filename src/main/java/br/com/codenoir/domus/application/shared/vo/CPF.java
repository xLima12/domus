package br.com.codenoir.domus.application.shared.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class CPF {

    @NotBlank(message = "CPF cannot blank")
    @NotNull(message = "CPF cannot null")
    @Column(name = "cpf")
    @Size(min = 11, max = 11, message = "CPF must have exactly 11 numeric digits")
    private String value;

    public CPF(String value) {
        this.value = value;
    }

}
