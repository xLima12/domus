package br.com.codenoir.domus.application.offerProperty.repository;

import br.com.codenoir.domus.application.offerProperty.entity.OfferPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OfferPropertyRepository extends JpaRepository<OfferPropertyEntity, UUID> {
}
