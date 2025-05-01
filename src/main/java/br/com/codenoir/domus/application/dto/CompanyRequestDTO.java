package br.com.codenoir.domus.application.dto;

import br.com.codenoir.domus.application.vo.CNPJ;
import br.com.codenoir.domus.application.vo.EmailAddress;
import br.com.codenoir.domus.application.vo.Password;
import br.com.codenoir.domus.application.vo.Username;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyRequestDTO {

    @NotBlank(message = "Name cannot blank")
    private String name;

    @Valid
    private Username username;

    @Valid
    private Password password;

    @Valid
    private CNPJ cnpj;

    @Valid
    private EmailAddress emailAddress;

}
