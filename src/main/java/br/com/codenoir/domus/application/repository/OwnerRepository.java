package br.com.codenoir.domus.application.repository;

import br.com.codenoir.domus.application.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<OwnerEntity, UUID> {
}
