package br.com.codenoir.domus.application.service;

import br.com.codenoir.domus.application.dto.OfferPropertyRequestDTO;
import br.com.codenoir.domus.application.entity.OfferPropertyEntity;
import br.com.codenoir.domus.application.enums.OfferType;
import br.com.codenoir.domus.application.repository.OfferPropertyRepository;
import br.com.codenoir.domus.application.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferPropertyService {

    @Autowired
    OfferPropertyRepository offerPropertyRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public Optional<OfferPropertyEntity> findById(UUID id) {
        return offerPropertyRepository.findById(id);
    }

    public List<OfferPropertyEntity> findAll() {
        return offerPropertyRepository.findAll();
    }

    public OfferPropertyEntity create(OfferPropertyRequestDTO offerPropertyRequestDTO) {
        var property = propertyRepository.findById(UUID.fromString(offerPropertyRequestDTO.getProperty_id()))
            .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        var offerProperty = new OfferPropertyEntity();

        offerProperty.setOfferType(OfferType.fromCode(offerPropertyRequestDTO.getOfferType()));
        offerProperty.setPropertyId(property);
        offerProperty.setPrice(new BigDecimal(String.valueOf(offerPropertyRequestDTO.getPrice())));

        return offerPropertyRepository.save(offerProperty);
    }

    public OfferPropertyEntity update(UUID id, OfferPropertyRequestDTO offerPropertyRequestDTO) {
        var property = propertyRepository.findById(UUID.fromString(offerPropertyRequestDTO.getProperty_id()))
            .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        return offerPropertyRepository.findById(id).map(existingOfferProperty -> {
            existingOfferProperty.setOfferType(OfferType.fromCode(offerPropertyRequestDTO.getOfferType()));
            existingOfferProperty.setPropertyId(property);
            existingOfferProperty.setPrice(new BigDecimal(String.valueOf(offerPropertyRequestDTO.getPrice())));
            return offerPropertyRepository.save(existingOfferProperty);
        }).orElseThrow(() -> new IllegalArgumentException("Offer property not found with id: " + id));
    }

    public boolean delete(UUID id) {
        if(offerPropertyRepository.existsById(id)) {
            offerPropertyRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
