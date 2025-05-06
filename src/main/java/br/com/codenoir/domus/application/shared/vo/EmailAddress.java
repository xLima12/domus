package br.com.codenoir.domus.application.shared.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class EmailAddress {

    @NotBlank(message = "E-mail cannot blank")
    @NotNull(message = "E-mail cannot null")
    @Email(message = "E-mail is invalid")
    @Column(name = "email")
    private String value;

    public EmailAddress(String value) {
        this.value = value;
    }

}
