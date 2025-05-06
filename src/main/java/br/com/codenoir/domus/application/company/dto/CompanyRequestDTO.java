package br.com.codenoir.domus.application.company.dto;

import br.com.codenoir.domus.application.shared.enums.Roles;
import br.com.codenoir.domus.application.shared.vo.CNPJ;
import br.com.codenoir.domus.application.shared.vo.EmailAddress;
import br.com.codenoir.domus.application.shared.vo.Password;
import br.com.codenoir.domus.application.shared.vo.Username;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

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

    private List<@NotNull(message = "Roles cannot null") Roles> roles;

}
