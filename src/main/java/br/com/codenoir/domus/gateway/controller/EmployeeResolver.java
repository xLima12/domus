package br.com.codenoir.domus.gateway.controller;

import br.com.codenoir.domus.application.auth.dto.AuthRequestDTO;
import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.employee.dto.EmployeeRequestDTO;
import br.com.codenoir.domus.application.employee.entity.EmployeeEntity;
import br.com.codenoir.domus.application.employee.service.AuthEmployeeService;
import br.com.codenoir.domus.application.employee.service.EmployeeService;
import br.com.codenoir.domus.application.security.DomusGraphQLContext;
import br.com.codenoir.domus.application.security.DomusRolesAllowed;
import graphql.schema.DataFetchingEnvironment;
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
    private EmployeeService employeeService;

    @Autowired
    private AuthEmployeeService authEmployeeService;

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @QueryMapping
    public Optional<EmployeeEntity> getEmployee(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return employeeService.findByIdEmployee(id);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @QueryMapping
    public List<EmployeeEntity> getAllEmployees(DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return employeeService.findAllEmployees();
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public EmployeeEntity createEmployee(@Argument @Valid EmployeeRequestDTO input,
                                         DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return employeeService.create(input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public EmployeeEntity updateEmployee(@Argument UUID id,
                                         @Argument @Valid EmployeeRequestDTO input,
                                         DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return employeeService.update(id, input);
    }

    @DomusRolesAllowed(value = {"MASTER", "COMPANY", "ADMIN"})
    @MutationMapping
    public Boolean deleteEmployee(@Argument UUID id, DataFetchingEnvironment environment) {
        DomusGraphQLContext userContext = environment.getGraphQlContext().get("auth");
        return employeeService.delete(id);
    }

    @MutationMapping
    public AuthResponseDTO signInEmployee(@Argument AuthRequestDTO input) {
        return authEmployeeService.signIn(input);
    }

}
