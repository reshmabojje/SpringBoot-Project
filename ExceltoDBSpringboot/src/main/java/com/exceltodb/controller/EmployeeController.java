/*
package com.exceltodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exceltodb.model.Employee;
import com.exceltodb.service.EmployeeService;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{empid}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String empid) {
        Employee employee = employeeService.getEmployeeById(empid);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{empid}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String empid, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(empid, employee);
        if (updatedEmployee != null) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{empid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String empid) {
        employeeService.deleteEmployee(empid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
*/
///////////////////////////////////////////////////////
package com.exceltodb.controller;

import com.exceltodb.model.Employee;
import com.exceltodb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{empid}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String empid) {
        Employee employee = employeeService.getEmployeeById(empid);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{empid}")
    public ResponseEntity<?> updateEmployee(@PathVariable String empid, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(empid, employee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{empid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String empid) {
        employeeService.deleteEmployee(empid);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
