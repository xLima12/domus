package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.property.dto.PropertyRequestDTO;
import br.com.codenoir.domus.application.property.entity.PropertyEntity;
import br.com.codenoir.domus.application.property.service.PropertyService;
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
public class PropertyResolver {

    @Autowired
    private PropertyService propertyService;

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @QueryMapping
    public Optional<PropertyEntity> getProperty(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return propertyService.findById(id);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @QueryMapping
    public List<PropertyEntity> getAllProperties(DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return propertyService.findAll();
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public PropertyEntity createProperty(@Argument @Valid PropertyRequestDTO input, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return propertyService.create(input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public PropertyEntity updateProperty(@Argument UUID id, @Argument @Valid PropertyRequestDTO input, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return propertyService.update(id, input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public boolean deleteProperty(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return propertyService.delete(id);
    }

}
