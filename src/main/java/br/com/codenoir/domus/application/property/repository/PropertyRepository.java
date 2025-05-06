package br.com.codenoir.domus.application.property.repository;

import br.com.codenoir.domus.application.property.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<PropertyEntity, UUID> {
}
