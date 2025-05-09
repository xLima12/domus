package br.com.codenoir.domus.application.offerProperty.service;

import br.com.codenoir.domus.application.exception.NotFoundException;
import br.com.codenoir.domus.application.offerProperty.dto.OfferPropertyRequestDTO;
import br.com.codenoir.domus.application.offerProperty.entity.OfferPropertyEntity;
import br.com.codenoir.domus.application.shared.enums.OfferType;
import br.com.codenoir.domus.application.offerProperty.repository.OfferPropertyRepository;
import br.com.codenoir.domus.application.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferPropertyService {

    @Autowired
    private OfferPropertyRepository offerPropertyRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    public Optional<OfferPropertyEntity> findById(UUID id) {
        return offerPropertyRepository.findById(id);
    }

    public List<OfferPropertyEntity> findAll() {
        return offerPropertyRepository.findAll();
    }

    public OfferPropertyEntity create(OfferPropertyRequestDTO offerPropertyRequestDTO) {
        var property = propertyRepository.findById(UUID.fromString(offerPropertyRequestDTO.getProperty_id()))
            .orElseThrow(() -> new NotFoundException("Property not found"));

        var offerProperty = new OfferPropertyEntity();

        offerProperty.setOfferType(OfferType.fromCode(offerPropertyRequestDTO.getOfferType()));
        offerProperty.setPropertyId(property);
        offerProperty.setPrice(new BigDecimal(String.valueOf(offerPropertyRequestDTO.getPrice())));

        return offerPropertyRepository.save(offerProperty);
    }

    public OfferPropertyEntity update(UUID id, OfferPropertyRequestDTO offerPropertyRequestDTO) {
        var property = propertyRepository.findById(UUID.fromString(offerPropertyRequestDTO.getProperty_id()))
            .orElseThrow(() -> new NotFoundException("Property not found"));

        return offerPropertyRepository.findById(id).map(existingOfferProperty -> {
            existingOfferProperty.setOfferType(OfferType.fromCode(offerPropertyRequestDTO.getOfferType()));
            existingOfferProperty.setPropertyId(property);
            existingOfferProperty.setPrice(new BigDecimal(String.valueOf(offerPropertyRequestDTO.getPrice())));
            return offerPropertyRepository.save(existingOfferProperty);
        }).orElseThrow(() -> new NotFoundException("Offer property not found"));
    }

    public boolean delete(UUID id) {
        if(offerPropertyRepository.existsById(id)) {
            offerPropertyRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
