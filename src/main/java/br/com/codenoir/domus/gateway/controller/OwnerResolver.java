package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.owner.dto.OwnerRequestDTO;
import br.com.codenoir.domus.application.owner.entity.OwnerEntity;
import br.com.codenoir.domus.application.owner.service.OwnerService;
import br.com.codenoir.domus.application.security.DomusGraphQLContext;
import br.com.codenoir.domus.application.security.DomusRolesAllowed;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class OwnerResolver {

    @Autowired
    private OwnerService ownerService;

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @QueryMapping
    public Optional<OwnerEntity> getOwner(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return ownerService.findById(id);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @QueryMapping
    public List<OwnerEntity> getAllOwners(DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return ownerService.findAll();
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public OwnerEntity createOwner(@Argument OwnerRequestDTO input, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return ownerService.create(input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public OwnerEntity updateOwner(@Argument UUID id,
                                   @Argument OwnerRequestDTO input,
                                   DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return ownerService.update(id, input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public boolean deleteOwner(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return ownerService.delete(id);
    }

}
