package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.auth.dto.AuthRequestDTO;
import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.company.dto.CompanyRequestDTO;
import br.com.codenoir.domus.application.company.entity.CompanyEntity;
import br.com.codenoir.domus.application.company.service.AuthCompanyService;
import br.com.codenoir.domus.application.company.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CompanyResolver {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AuthCompanyService authCompanyService;

    @QueryMapping
    public Optional<CompanyEntity> getCompany(@Argument UUID id) {
        return companyService.findById(id);
    }

    @QueryMapping
    public List<CompanyEntity> getAllCompanies() {
        return companyService.findAll();
    }

    @MutationMapping
    public CompanyEntity createCompany(@Argument @Valid CompanyRequestDTO input) {
        return companyService.create(input);
    }

    @MutationMapping
    public CompanyEntity updateCompany(@Argument UUID id, @Argument @Valid CompanyRequestDTO input) {
        return companyService.update(id, input);
    }

    @MutationMapping
    public Boolean deleteCompany(@Argument UUID id) {
        return companyService.delete(id);
    }

    @MutationMapping
    public AuthResponseDTO signInCompany(@Argument AuthRequestDTO input) {
        return authCompanyService.signIn(input);
    }

}
