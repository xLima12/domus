package br.com.codenoir.domus.application.owner.repository;

import br.com.codenoir.domus.application.owner.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<OwnerEntity, UUID> {
}
