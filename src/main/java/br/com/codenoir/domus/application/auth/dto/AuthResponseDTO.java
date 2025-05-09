package br.com.codenoir.domus.application.auth.dto;

import br.com.codenoir.domus.application.shared.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String expiresIn;
    private List<Roles> roles;

}
