package br.com.codenoir.domus.application.auth.dto;

import br.com.codenoir.domus.application.shared.vo.Password;
import br.com.codenoir.domus.application.shared.vo.Username;
import lombok.Data;

@Data
public class AuthRequestDTO {

    private Username username;
    private Password password;

}
