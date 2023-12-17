package com.hemanth.employeemanagment.utils;

import com.hemanth.employeemanagment.EmployeeEntity;
import com.hemanth.employeemanagment.exception.ResourceNotFoundException;
import com.hemanth.employeemanagment.model.CreateEmployeeRequest;
import com.hemanth.employeemanagment.model.Employee;
import com.hemanth.employeemanagment.model.UpdateEmployeeRequest;

public class EmployeeUtils {

    public static EmployeeEntity mapToEmployeeEntity(CreateEmployeeRequest request) {
        if (request == null) {
            return null;
        }

        EmployeeEntity entity = new EmployeeEntity();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());

        return entity;
    }

    public static Employee mapToEmployee(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(entity.getId());
        employee.setFirstName(entity.getFirstName());
        employee.setLastName(entity.getLastName());
        employee.setEmail(entity.getEmail());

        return employee;
    }

    public static EmployeeEntity updateEmployeeEntityFromRequest(UpdateEmployeeRequest request, EmployeeEntity entity) {
        if (request == null || entity == null) {
            throw new ResourceNotFoundException(" Entity or fields should not be null"); // Or handle this case as appropriate for your application
        }

        if (request.getId() != null) {
            entity.setId(request.getId());
        }
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }

        return entity;
    }


}
