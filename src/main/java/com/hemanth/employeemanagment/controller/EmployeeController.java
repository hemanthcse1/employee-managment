package com.hemanth.employeemanagment.controller;

import com.hemanth.employeemanagment.model.CreateEmployeeRequest;
import com.hemanth.employeemanagment.model.Employee;
import com.hemanth.employeemanagment.model.UpdateEmployeeRequest;
import com.hemanth.employeemanagment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(createEmployeeRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(updateEmployeeRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
