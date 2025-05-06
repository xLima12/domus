package br.com.codenoir.domus.application.company.repository;

import br.com.codenoir.domus.application.company.entity.CompanyEntity;
import br.com.codenoir.domus.application.shared.vo.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsername(Username username);
}
