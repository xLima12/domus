package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.auth.dto.AuthRequestDTO;
import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.company.dto.CompanyRequestDTO;
import br.com.codenoir.domus.application.company.entity.CompanyEntity;
import br.com.codenoir.domus.application.company.service.AuthCompanyService;
import br.com.codenoir.domus.application.company.service.CompanyService;
import br.com.codenoir.domus.application.security.DomusGraphQLContext;
import br.com.codenoir.domus.application.security.DomusRolesAllowed;
import graphql.schema.DataFetchingEnvironment;
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

    @DomusRolesAllowed(value = {"MASTER"})
    @QueryMapping
    public Optional<CompanyEntity> getCompany(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return companyService.findById(id);
    }

    @DomusRolesAllowed(value = {"MASTER"})
    @QueryMapping
    public List<CompanyEntity> getAllCompanies(DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return companyService.findAll();
    }

    @MutationMapping
    public CompanyEntity createCompany(@Argument @Valid CompanyRequestDTO input, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return companyService.create(input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY"})
    @MutationMapping
    public CompanyEntity updateCompany(@Argument UUID id,
                                       @Argument @Valid CompanyRequestDTO input,
                                       DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return companyService.update(id, input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY"})
    @MutationMapping
    public Boolean deleteCompany(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return companyService.delete(id);
    }

    @MutationMapping
    public AuthResponseDTO signInCompany(@Argument AuthRequestDTO input) {
        return authCompanyService.signIn(input);
    }

}
