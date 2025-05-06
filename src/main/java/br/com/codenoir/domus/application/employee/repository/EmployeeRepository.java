package br.com.codenoir.domus.application.employee.repository;

import br.com.codenoir.domus.application.employee.entity.EmployeeEntity;
import br.com.codenoir.domus.application.shared.vo.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository <EmployeeEntity, UUID> {
    Optional<EmployeeEntity> findByUsername(Username username);
}
