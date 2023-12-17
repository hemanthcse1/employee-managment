package com.hemanth.employeemanagment.service;

import com.hemanth.employeemanagment.EmployeeEntity;
import com.hemanth.employeemanagment.exception.ResourceNotFoundException;
import com.hemanth.employeemanagment.model.CreateEmployeeRequest;
import com.hemanth.employeemanagment.model.Employee;
import com.hemanth.employeemanagment.model.UpdateEmployeeRequest;
import com.hemanth.employeemanagment.repository.EmployeeRepository;
import com.hemanth.employeemanagment.utils.EmployeeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest) {
        EmployeeEntity persistedEmployee = employeeRepository.save(EmployeeUtils.mapToEmployeeEntity(createEmployeeRequest));
        return EmployeeUtils.mapToEmployee(persistedEmployee);
    }

    public Employee getEmployee(Long id) {
        EmployeeEntity persistedEmployee = employeeRepository.findById(id).orElse(null);

        if (persistedEmployee == null) {
           throw new ResourceNotFoundException("Employee not found with id: " + id);
        }

        return EmployeeUtils.mapToEmployee(persistedEmployee);
    }

    public Employee updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) {
        EmployeeEntity persistedEmployee = employeeRepository.findById(updateEmployeeRequest.getId()).orElse(null);

        if (persistedEmployee == null) {
           throw new ResourceNotFoundException("Employee not found with id: " + updateEmployeeRequest.getId());
        }
        EmployeeEntity updatedEmployee = employeeRepository.save(EmployeeUtils.updateEmployeeEntityFromRequest(updateEmployeeRequest, persistedEmployee));
        return EmployeeUtils.mapToEmployee(updatedEmployee);
    }

    public Map<String,String> deleteEmployee(Long id) {
        EmployeeEntity persistedEmployee = employeeRepository.findById(id).orElse(null);

        if (persistedEmployee == null) {
           throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
        return Map.of("message", "Employee deleted successfully");
    }

    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> persistedEmployees = employeeRepository.findAll();
        return persistedEmployees.stream()
                .map(EmployeeUtils::mapToEmployee)
                .collect(Collectors.toList());
    }
}
