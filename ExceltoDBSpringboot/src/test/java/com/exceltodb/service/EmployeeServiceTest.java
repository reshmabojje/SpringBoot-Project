
package com.exceltodb.service;

import com.exceltodb.model.Employee;
import com.exceltodb.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        // Mock data
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("1", "John", "Doe", "Manager", "Project A", "Active"));
        employees.add(new Employee("2", "Jane", "Doe", "Developer", "Project B", "Inactive"));

        when(employeeRepository.findAll()).thenReturn(employees);

        // Call service method
        List<Employee> result = employeeService.getAllEmployees();

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    void testGetEmployeeById() {
        // Mock data
        String empid = "1";
        Employee employee = new Employee("1", "John", "Doe", "Manager", "Project A", "Active");

        when(employeeRepository.findById(empid)).thenReturn(Optional.of(employee));

        // Call service method
        Employee result = employeeService.getEmployeeById(empid);

        // Verify the result
        assertEquals(employee, result);
    }

    @Test
    void testCreateEmployee() {
        // Mock data
        Employee employee = new Employee("1", "John", "Doe", "Manager", "Project A", "Active");

        when(employeeRepository.save(employee)).thenReturn(employee);

        // Call service method
        Employee result = employeeService.createEmployee(employee);

        // Verify the result
        assertEquals(employee, result);
    }

    @Test
    void testUpdateEmployee() {
        // Mock data
        String empid = "1";
        Employee existingEmployee = new Employee("1", "John", "Doe", "Manager", "Project A", "Active");
        Employee updatedEmployee = new Employee("1", "John", "Doe", "Manager", "Project B", "Active");

        when(employeeRepository.existsById(empid)).thenReturn(true);
        when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);

        // Call service method
        Employee result = employeeService.updateEmployee(empid, updatedEmployee);

        // Verify the result
        assertEquals(updatedEmployee, result);
    }

    @Test
    void testDeleteEmployee() {
        // Mock data
        String empid = "1";

        // Call service method
        employeeService.deleteEmployee(empid);

        // Verify that delete method of repository is called
        verify(employeeRepository, times(1)).deleteById(empid);
    }
}

