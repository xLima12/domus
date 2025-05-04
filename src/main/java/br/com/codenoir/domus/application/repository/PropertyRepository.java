package br.com.codenoir.domus.application.repository;

import br.com.codenoir.domus.application.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<PropertyEntity, UUID> {
}
