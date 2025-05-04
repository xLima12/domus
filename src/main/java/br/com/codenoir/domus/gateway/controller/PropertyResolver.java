package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.dto.PropertyRequestDTO;
import br.com.codenoir.domus.application.entity.PropertyEntity;
import br.com.codenoir.domus.application.service.PropertyService;
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
    PropertyService propertyService;

    @QueryMapping
    public Optional<PropertyEntity> getProperty(@Argument UUID id) {
        return propertyService.findById(id);
    }

    @QueryMapping
    public List<PropertyEntity> getAllProperties() {
        return propertyService.findAll();
    }

    @MutationMapping
    public PropertyEntity createProperty(@Argument @Valid PropertyRequestDTO input) {
        return propertyService.create(input);
    }

    @MutationMapping
    public PropertyEntity updateProperty(@Argument UUID id, @Argument @Valid PropertyRequestDTO input) {
        return propertyService.update(id, input);
    }

    @MutationMapping
    public boolean deleteProperty(@Argument UUID id) {
        return propertyService.delete(id);
    }

}
