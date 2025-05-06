package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.owner.dto.OwnerRequestDTO;
import br.com.codenoir.domus.application.owner.entity.OwnerEntity;
import br.com.codenoir.domus.application.owner.service.OwnerService;
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
    OwnerService ownerService;

    @QueryMapping
    public Optional<OwnerEntity> getOwner(@Argument UUID id) {
        return ownerService.findById(id);
    }

    @QueryMapping
    public List<OwnerEntity> getAllOwners() {
        return ownerService.findAll();
    }

    @MutationMapping
    public OwnerEntity createOwner(@Argument OwnerRequestDTO input) {
        System.out.println("Dados da requisiçao:");
        System.out.println(input.getOwnerType());
        return ownerService.create(input);
    }

    @MutationMapping
    public OwnerEntity updateOwner(@Argument UUID id, @Argument OwnerRequestDTO input) {
        return ownerService.update(id, input);
    }

    @MutationMapping
    public boolean deleteOwner(@Argument UUID id) {
        return ownerService.delete(id);
    }

}
