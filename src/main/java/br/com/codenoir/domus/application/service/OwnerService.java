package br.com.codenoir.domus.application.service;

import br.com.codenoir.domus.application.dto.OwnerRequestDTO;
import br.com.codenoir.domus.application.entity.OwnerEntity;
import br.com.codenoir.domus.application.enums.OwnerType;
import br.com.codenoir.domus.application.repository.OwnerRepository;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.vo.CNPJ;
import br.com.codenoir.domus.application.vo.CPF;
import br.com.codenoir.domus.application.vo.EmailAddress;
import br.com.codenoir.domus.application.vo.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    BCryptPasswordEncoderService passwordEncoderService;

    public Optional<OwnerEntity> findById(UUID id) {
        return ownerRepository.findById(id);
    }

    public List<OwnerEntity> findAll() {
        return ownerRepository.findAll();
    }

    public OwnerEntity create(OwnerRequestDTO ownerRequestDTO) {
        var encodedPassword = passwordEncoderService.encode(ownerRequestDTO.getPassword().getValue());
        var owner = new OwnerEntity();
        var ownerType = OwnerType.fromCode(ownerRequestDTO.getOwnerType());


        owner.setUsername(ownerRequestDTO.getUsername());
        owner.setPassword(new Password(encodedPassword));
        owner.setEmailAddress(new EmailAddress(ownerRequestDTO.getEmailAddress().getValue()));
        owner.setFirstName(ownerRequestDTO.getFirstName());
        owner.setLastName(ownerRequestDTO.getLastName());
        owner.setOwnerType(ownerType);

        switch (ownerType) {
            case LEGAL_ENTITY -> owner.setCnpj(new CNPJ(ownerRequestDTO.getDocument()));
            case INDIVIDUAL -> owner.setCpf(new CPF(ownerRequestDTO.getDocument()));
        }

        return ownerRepository.save(owner);
    }

    public OwnerEntity update(UUID id, OwnerRequestDTO ownerRequestDTO) {
        var ownerType = OwnerType.fromCode(ownerRequestDTO.getOwnerType());
        return ownerRepository.findById(id).map(existingOwner -> {
            existingOwner.setUsername(ownerRequestDTO.getUsername());
            existingOwner.setEmailAddress(new EmailAddress(ownerRequestDTO.getEmailAddress().getValue()));
            existingOwner.setFirstName(ownerRequestDTO.getFirstName());
            existingOwner.setLastName(ownerRequestDTO.getLastName());
            existingOwner.setOwnerType(ownerType);
            switch (ownerType) {
                case LEGAL_ENTITY -> existingOwner.setCnpj(new CNPJ(ownerRequestDTO.getDocument()));
                case INDIVIDUAL -> existingOwner.setCpf(new CPF(ownerRequestDTO.getDocument()));
            }
            return ownerRepository.save(existingOwner);
        }).orElseThrow(() -> new IllegalArgumentException("Owner not found"));
    }

    public boolean delete(UUID id) {
        if(ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
