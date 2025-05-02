package br.com.codenoir.domus.application.service;

import br.com.codenoir.domus.application.dto.EmployeeRequestDTO;
import br.com.codenoir.domus.application.entity.EmployeeEntity;
import br.com.codenoir.domus.application.repository.CompanyRepository;
import br.com.codenoir.domus.application.repository.EmployeeRepository;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.vo.EmailAddress;
import br.com.codenoir.domus.application.vo.Password;
import br.com.codenoir.domus.application.vo.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    BCryptPasswordEncoderService passwordEncoder;

    public Optional<EmployeeEntity> findByIdEmployee(UUID id) {
        return employeeRepository.findById(id);
    }

    public List<EmployeeEntity> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity create(EmployeeRequestDTO employeeRequest) {
        var encodedPassword = passwordEncoder.encode(employeeRequest.getPassword().getValue());

        var employee = new EmployeeEntity();

        var company = companyRepository.findById(UUID.fromString(employeeRequest.getCompany_id()))
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        employee.setUsername(new Username(employeeRequest.getUsername().getValue()));
        employee.setPassword(new Password(encodedPassword));
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmailAddress(new EmailAddress(employeeRequest.getEmailAddress().getValue()));
        employee.setCompany(company);
        employee.setRoles(new ArrayList<>(employeeRequest.getRoles()));

        return employeeRepository.save(employee);
    }

    public EmployeeEntity update(UUID id, EmployeeRequestDTO employeeRequest) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setUsername(employeeRequest.getUsername());
            existingEmployee.setFirstName(employeeRequest.getFirstName());
            existingEmployee.setLastName(employeeRequest.getLastName());
            existingEmployee.setEmailAddress(new EmailAddress(employeeRequest.getEmailAddress().getValue()));
            existingEmployee.setRoles(new ArrayList<>(employeeRequest.getRoles()));
            return employeeRepository.save(existingEmployee);
        }).orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }

    public Boolean delete(UUID id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
