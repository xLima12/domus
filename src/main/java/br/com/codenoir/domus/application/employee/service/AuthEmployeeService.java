package br.com.codenoir.domus.application.employee.service;

import br.com.codenoir.domus.application.auth.dto.AuthRequestDTO;
import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.auth.service.AuthService;
import br.com.codenoir.domus.application.employee.entity.EmployeeEntity;
import br.com.codenoir.domus.application.employee.repository.EmployeeRepository;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.shared.vo.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthEmployeeService {

    @Value("${security.token.secret.employee}")
    private String secretKey;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private BCryptPasswordEncoderService passwordEncoder;

    public AuthResponseDTO signIn(AuthRequestDTO authRequestDTO) {
        return authService.authenticate(
            secretKey,
            employeeRepository.findByUsername(new Username(authRequestDTO.getUsername().getValue())),
            employee -> employee.getId().toString(),
            employee -> employee.getPassword().getValue(),
            EmployeeEntity::getRoles,
            authRequestDTO.getPassword().getValue()
        );
    }

}
