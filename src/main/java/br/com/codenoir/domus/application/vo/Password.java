package br.com.codenoir.domus.application.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class Password {

    @NotBlank(message = "Password cannot blank")
    @NotNull(message = "Password cannot null")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Column(name = "password")
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public static Password encrypted(String encryptedValue) {
        return new Password(encryptedValue);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Password password)) return false;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "******";
    }

}
