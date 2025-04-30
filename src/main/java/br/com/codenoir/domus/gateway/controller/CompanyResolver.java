package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.dto.CompanyRequestDTO;
import br.com.codenoir.domus.application.entity.CompanyEntity;
import br.com.codenoir.domus.application.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompanyResolver {

    @Autowired
    private CompanyService companyService;

    @MutationMapping
    public CompanyEntity createCompany(@Argument("input") CompanyRequestDTO input) {
        return companyService.create(input);
    }

    @QueryMapping
    public List<CompanyEntity> companies() {
        return companyService.findAll();
    }

}
