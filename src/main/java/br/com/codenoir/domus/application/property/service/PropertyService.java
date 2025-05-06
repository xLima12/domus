package br.com.codenoir.domus.application.property.service;

import br.com.codenoir.domus.application.property.dto.PropertyRequestDTO;
import br.com.codenoir.domus.application.property.entity.PropertyEntity;
import br.com.codenoir.domus.application.owner.repository.OwnerRepository;
import br.com.codenoir.domus.application.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public Optional<PropertyEntity> findById(UUID id) {
        return propertyRepository.findById(id);
    }

    public List<PropertyEntity> findAll() {
        return propertyRepository.findAll();
    }

    public PropertyEntity create(PropertyRequestDTO propertyRequestDTO) {
        var owner = ownerRepository.findById(UUID.fromString(propertyRequestDTO.getOwner_id()))
            .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        var property = new PropertyEntity();

        property.setName(propertyRequestDTO.getName());
        property.setAddress(propertyRequestDTO.getAddress());
        property.setBedrooms(propertyRequestDTO.getBedrooms());
        property.setBathroom(propertyRequestDTO.getBathroom());
        property.setKitchen(propertyRequestDTO.getKitchen());
        property.setLivingRoom(propertyRequestDTO.getLivingRoom());
        property.setGarage(propertyRequestDTO.getGarage());
        property.setBasement(propertyRequestDTO.getBasement());
        property.setOwner(owner);

        return propertyRepository.save(property);
    }

    public PropertyEntity update(UUID id, PropertyRequestDTO propertyRequestDTO) {
        var owner = ownerRepository.findById(UUID.fromString(propertyRequestDTO.getOwner_id()))
            .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        return propertyRepository.findById(id).map(existingProperty -> {
            existingProperty.setName(propertyRequestDTO.getName());
            existingProperty.setAddress(propertyRequestDTO.getAddress());
            existingProperty.setBedrooms(propertyRequestDTO.getBedrooms());
            existingProperty.setBathroom(propertyRequestDTO.getBathroom());
            existingProperty.setKitchen(propertyRequestDTO.getKitchen());
            existingProperty.setLivingRoom(propertyRequestDTO.getLivingRoom());
            existingProperty.setGarage(propertyRequestDTO.getGarage());
            existingProperty.setBasement(propertyRequestDTO.getBasement());
            existingProperty.setOwner(owner);
            return propertyRepository.save(existingProperty);
        }).orElseThrow(() -> new IllegalArgumentException("Property not found"));
    }

    public boolean delete(UUID id) {
        if(propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
