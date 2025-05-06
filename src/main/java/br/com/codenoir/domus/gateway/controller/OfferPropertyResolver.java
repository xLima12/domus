package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.offerProperty.dto.OfferPropertyRequestDTO;
import br.com.codenoir.domus.application.offerProperty.entity.OfferPropertyEntity;
import br.com.codenoir.domus.application.offerProperty.service.OfferPropertyService;
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
    OfferPropertyService offerPropertyService;

    @QueryMapping
    public Optional<OfferPropertyEntity> getOfferProperty(@Argument UUID id) {
        return offerPropertyService.findById(id);
    }

    @QueryMapping
    public List<OfferPropertyEntity> getAllOfferProperties() {
        return offerPropertyService.findAll();
    }

    @MutationMapping
    public OfferPropertyEntity createOfferProperty(@Argument @Valid OfferPropertyRequestDTO input) {
        return offerPropertyService.create(input);
    }

    @MutationMapping
    public OfferPropertyEntity updateOfferProperty(@Argument UUID id, @Argument @Valid OfferPropertyRequestDTO input) {
        return offerPropertyService.update(id, input);
    }

    @MutationMapping
    public boolean deleteOfferProperty(@Argument UUID id) {
        return offerPropertyService.delete(id);
    }

}
