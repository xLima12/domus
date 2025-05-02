package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.dto.OfferPropertyRequestDTO;
import br.com.codenoir.domus.application.entity.OfferPropertyEntity;
import br.com.codenoir.domus.application.service.OfferPropertyService;
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
    public OfferPropertyEntity createOfferProperty(@Argument @Valid OfferPropertyRequestDTO offerPropertyRequestDTO) {
        return offerPropertyService.create(offerPropertyRequestDTO);
    }

    @MutationMapping
    public OfferPropertyEntity updateOfferProperty(@Argument UUID id, @Argument @Valid OfferPropertyRequestDTO offerPropertyRequestDTO) {
        return offerPropertyService.update(id, offerPropertyRequestDTO);
    }

    @MutationMapping
    public boolean deleteOfferProperty(@Argument UUID id) {
        return offerPropertyService.delete(id);
    }

}
