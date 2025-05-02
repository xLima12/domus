package br.com.codenoir.domus.application.repository;

import br.com.codenoir.domus.application.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository <EmployeeEntity, UUID> {
}
