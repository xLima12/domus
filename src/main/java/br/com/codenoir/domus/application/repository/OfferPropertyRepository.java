package br.com.codenoir.domus.application.repository;

import br.com.codenoir.domus.application.entity.OfferPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OfferPropertyRepository extends JpaRepository<OfferPropertyEntity, UUID> {
}
