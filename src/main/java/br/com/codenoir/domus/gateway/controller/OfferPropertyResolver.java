package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.offerProperty.dto.OfferPropertyRequestDTO;
import br.com.codenoir.domus.application.offerProperty.entity.OfferPropertyEntity;
import br.com.codenoir.domus.application.offerProperty.service.OfferPropertyService;
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
public class OfferPropertyResolver {

    @Autowired
    private OfferPropertyService offerPropertyService;

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @QueryMapping
    public Optional<OfferPropertyEntity> getOfferProperty(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return offerPropertyService.findById(id);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @QueryMapping
    public List<OfferPropertyEntity> getAllOfferProperties(DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return offerPropertyService.findAll();
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public OfferPropertyEntity createOfferProperty(@Argument @Valid OfferPropertyRequestDTO input,
                                                   DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return offerPropertyService.create(input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public OfferPropertyEntity updateOfferProperty(@Argument UUID id,
                                                   @Argument @Valid OfferPropertyRequestDTO input,
                                                   DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return offerPropertyService.update(id, input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public boolean deleteOfferProperty(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return offerPropertyService.delete(id);
    }

}
