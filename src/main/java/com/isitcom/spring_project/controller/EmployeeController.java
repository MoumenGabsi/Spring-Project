package com.isitcom.spring_project.controller;


import com.isitcom.spring_project.entity.Employee;
import com.isitcom.spring_project.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired

    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setNumber(employee.getNumber());
            existingEmployee.setAltNumber(employee.getAltNumber());
            existingEmployee.setCity(employee.getCity());
            existingEmployee.setState(employee.getState());
            existingEmployee.setSkill(employee.getSkill());
            existingEmployee.setExperience(employee.getExperience());
            return employeeService.saveEmployee(existingEmployee);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee != null) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();  // Return 204 No Content
        } else {
            return ResponseEntity.status(404).body("Employee with ID " + id + " not found.");
        }
    }
}
