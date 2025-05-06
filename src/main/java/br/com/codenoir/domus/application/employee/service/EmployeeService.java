package br.com.codenoir.domus.application.employee.service;

import br.com.codenoir.domus.application.auth.dto.AuthRequestDTO;
import br.com.codenoir.domus.application.auth.dto.AuthResponseDTO;
import br.com.codenoir.domus.application.employee.dto.EmployeeRequestDTO;
import br.com.codenoir.domus.application.employee.entity.EmployeeEntity;
import br.com.codenoir.domus.application.company.repository.CompanyRepository;
import br.com.codenoir.domus.application.employee.repository.EmployeeRepository;
import br.com.codenoir.domus.application.security.BCryptPasswordEncoderService;
import br.com.codenoir.domus.application.shared.vo.EmailAddress;
import br.com.codenoir.domus.application.shared.vo.Password;
import br.com.codenoir.domus.application.shared.vo.Username;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BCryptPasswordEncoderService passwordEncoder;

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
        employee.setRoles(employeeRequest.getRoles());

        return employeeRepository.save(employee);
    }

    public EmployeeEntity update(UUID id, EmployeeRequestDTO employeeRequest) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setUsername(employeeRequest.getUsername());
            existingEmployee.setFirstName(employeeRequest.getFirstName());
            existingEmployee.setLastName(employeeRequest.getLastName());
            existingEmployee.setEmailAddress(new EmailAddress(employeeRequest.getEmailAddress().getValue()));
            existingEmployee.setRoles(employeeRequest.getRoles());
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
