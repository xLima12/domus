package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.dto.EmployeeRequestDTO;
import br.com.codenoir.domus.application.entity.EmployeeEntity;
import br.com.codenoir.domus.application.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class EmployeeResolver {

    @Autowired
    EmployeeService employeeService;

    @QueryMapping
    public Optional<EmployeeEntity> getEmployee(@Argument UUID id) {
        return employeeService.findByIdEmployee(id);
    }

    @QueryMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @MutationMapping
    public EmployeeEntity createEmployee(@Argument @Valid EmployeeRequestDTO input) {
        return employeeService.create(input);
    }

    @MutationMapping
    public EmployeeEntity updateEmployee(@Argument UUID id, @Argument @Valid EmployeeRequestDTO input) {
        return employeeService.update(id, input);
    }

    @MutationMapping
    public Boolean deleteEmployee(@Argument UUID id) {
        return employeeService.delete(id);
    }

}
