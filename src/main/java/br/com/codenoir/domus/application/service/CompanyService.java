package br.com.codenoir.domus.application.service;

import br.com.codenoir.domus.application.dto.CompanyRequestDTO;
import br.com.codenoir.domus.application.entity.CompanyEntity;
import br.com.codenoir.domus.application.repository.CompanyRepository;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.vo.CNPJ;
import br.com.codenoir.domus.application.vo.EmailAddress;
import br.com.codenoir.domus.application.vo.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity create(CompanyRequestDTO companyRequest) {
        var password = new BCryptPasswordEncoderService().encode(companyRequest.getPassword().getValue());
        var company = new CompanyEntity();
        company.setName(companyRequest.getName());
        company.setUsername(companyRequest.getUsername());
        company.setPassword(new Password(password));
        company.setCnpj(new CNPJ(companyRequest.getCnpj().getValue()));
        company.setEmailAddress(new EmailAddress(companyRequest.getEmailAddress().getValue()));

        return this.companyRepository.save(company);
    }

    public List<CompanyEntity> findAll() {
        return this.companyRepository.findAll();
    }

}
