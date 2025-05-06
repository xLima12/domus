package br.com.codenoir.domus.application.employee.entity;

import br.com.codenoir.domus.application.company.entity.CompanyEntity;
import br.com.codenoir.domus.application.shared.entity.UserEntity;
import br.com.codenoir.domus.application.shared.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "tb_employee")
@Data
public class EmployeeEntity extends UserEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @Enumerated(EnumType.STRING)
    private List<@NotNull Roles> roles;

}
