package br.com.codenoir.domus.application.company.service;

import br.com.codenoir.domus.application.auth.dto.AuthRequestDTO;
import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.auth.service.AuthService;
import br.com.codenoir.domus.application.company.entity.CompanyEntity;
import br.com.codenoir.domus.application.company.repository.CompanyRepository;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.shared.vo.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyService {

    @Value("${security.token.secret.company}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private BCryptPasswordEncoderService passwordEncoderService;

    public AuthResponseDTO signIn(AuthRequestDTO authRequestDTO) {
        return authService.authenticate(
            secretKey,
            companyRepository.findByUsername(new Username(authRequestDTO.getUsername().getValue())),
            company -> company.getId().toString(),
            company -> company.getPassword().getValue(),
            CompanyEntity::getRoles,
            authRequestDTO.getPassword().getValue()
        );
    }

}
