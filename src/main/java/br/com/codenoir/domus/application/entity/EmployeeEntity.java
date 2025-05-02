package br.com.codenoir.domus.application.entity;

import br.com.codenoir.domus.application.enums.Roles;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private List<@NotNull Roles> roles;

}
