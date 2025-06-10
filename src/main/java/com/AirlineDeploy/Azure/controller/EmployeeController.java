package com.AirlineDeploy.Azure.controller;

import com.AirlineDeploy.Azure.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employeeList = new ArrayList<>();
    private long idCounter = 1;

    // Create
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(idCounter++);
        employeeList.add(employee);
        return employee;
    }

    // Read all
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    // Read by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeList.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Update
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updated) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId().equals(id)) {
                updated.setId(id);
                employeeList.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        boolean removed = employeeList.removeIf(emp -> emp.getId().equals(id));
        return removed ? "Deleted ID " + id : "Not found";
    }
}
