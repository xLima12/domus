package br.com.codenoir.domus.application.employee.dto;

import br.com.codenoir.domus.application.shared.enums.Roles;
import br.com.codenoir.domus.application.shared.vo.EmailAddress;
import br.com.codenoir.domus.application.shared.vo.Password;
import br.com.codenoir.domus.application.shared.vo.Username;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeRequestDTO {

    @Valid
    private Username username;

    @Valid
    private Password password;

    @NotBlank(message = "Name cannot blank")
    private String firstName;

    @NotBlank(message = "Last name cannot blank")
    private String lastName;

    @Valid
    private EmailAddress emailAddress;

    @NotBlank(message = "Company id cannot blank")
    private String company_id;

    private List<@NotNull(message = "Roles cannot null") Roles> roles;

}
