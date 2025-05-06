package br.com.codenoir.domus.application.company.service;

import br.com.codenoir.domus.application.company.dto.CompanyRequestDTO;
import br.com.codenoir.domus.application.company.entity.CompanyEntity;
import br.com.codenoir.domus.application.company.repository.CompanyRepository;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.shared.vo.CNPJ;
import br.com.codenoir.domus.application.shared.vo.EmailAddress;
import br.com.codenoir.domus.application.shared.vo.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BCryptPasswordEncoderService passwordEncoder;

    public Optional<CompanyEntity> findById(UUID id) {
        return companyRepository.findById(id);
    }

    public List<CompanyEntity> findAll() {
        return companyRepository.findAll();
    }

    public CompanyEntity create(CompanyRequestDTO companyRequest) {
        var encodedPassword = passwordEncoder.encode(companyRequest.getPassword().getValue());
        var company = new CompanyEntity();
        company.setName(companyRequest.getName());
        company.setUsername(companyRequest.getUsername());
        company.setPassword(new Password(encodedPassword));
        company.setCnpj(new CNPJ(companyRequest.getCnpj().getValue()));
        company.setEmailAddress(new EmailAddress(companyRequest.getEmailAddress().getValue()));
        company.setRoles(companyRequest.getRoles());

        return companyRepository.save(company);
    }

    public CompanyEntity update(UUID id, CompanyRequestDTO companyRequest) {
        return companyRepository.findById(id).map(existingCompany -> {
            existingCompany.setName(companyRequest.getName());
            existingCompany.setUsername(companyRequest.getUsername());
            existingCompany.setCnpj(new CNPJ(companyRequest.getCnpj().getValue()));
            existingCompany.setEmailAddress(new EmailAddress(companyRequest.getEmailAddress().getValue()));
            return companyRepository.save(existingCompany);
        }).orElseThrow(() -> new IllegalArgumentException("Company not found with id: " + id));
    }

    public Boolean delete(UUID id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
