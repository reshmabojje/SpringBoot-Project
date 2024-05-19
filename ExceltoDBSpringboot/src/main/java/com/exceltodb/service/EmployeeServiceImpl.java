/*
package com.exceltodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exceltodb.model.Employee;
import com.exceltodb.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import java.util.Iterator;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String empid) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empid);
        return optionalEmployee.orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(String empid, Employee employee) {
        if (employeeRepository.existsById(empid)) {
            employee.setEmpid(empid); // Ensure the correct ID is set
            return employeeRepository.save(employee);
        }
        return null; // Employee with given ID doesn't exist
    }

    @Override
    public void deleteEmployee(String empid) {
        employeeRepository.deleteById(empid);
    }

    @Override
    public List<Employee> readDataFromExcel(String excelFilePath) {
        List<Employee> employees = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(new File(excelFilePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            DataFormatter dataFormatter = new DataFormatter();
            rowIterator.next(); // Skip header row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Employee employee = new Employee();
                employee.setEmpid(dataFormatter.formatCellValue(row.getCell(0)));
                employee.setFirstName(dataFormatter.formatCellValue(row.getCell(1)));
                employee.setLastName(dataFormatter.formatCellValue(row.getCell(2)));
                employee.setDesignation(dataFormatter.formatCellValue(row.getCell(3)));
                employee.setProject(dataFormatter.formatCellValue(row.getCell(4)));
                employee.setStatus(dataFormatter.formatCellValue(row.getCell(5)));
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
*/
///////////////////////////////////////////////
package com.exceltodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exceltodb.model.Employee;
import com.exceltodb.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String empid) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empid);
        return optionalEmployee.orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(String empid, Employee employee) {
        if (employeeRepository.existsById(empid)) {
            employee.setEmpid(empid); // Ensure the correct ID is set
            return employeeRepository.save(employee);
        }
        return null; // Employee with given ID doesn't exist
    }

    @Override
    public void deleteEmployee(String empid) {
        employeeRepository.deleteById(empid);
    }

    @Override
    public List<Employee> readDataFromExcel(String excelFilePath) {
        List<Employee> employees = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(new File(excelFilePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            DataFormatter dataFormatter = new DataFormatter();
            rowIterator.next(); // Skip header row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Employee employee = new Employee();
                employee.setEmpid(dataFormatter.formatCellValue(row.getCell(0)));
                employee.setFirstName(dataFormatter.formatCellValue(row.getCell(1)));
                employee.setLastName(dataFormatter.formatCellValue(row.getCell(2)));
                employee.setDesignation(dataFormatter.formatCellValue(row.getCell(3)));
                employee.setProject(dataFormatter.formatCellValue(row.getCell(4)));
                employee.setStatus(dataFormatter.formatCellValue(row.getCell(5)));
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> synchronizeWithExcel(String excelFilePath) {
        List<Employee> excelEmployees = readDataFromExcel(excelFilePath);
        List<Employee> databaseEmployees = getAllEmployees();
        
        // Create a map of empid to Excel employees for easier lookup
        Map<String, Employee> excelEmployeeMap = excelEmployees.stream()
                .collect(Collectors.toMap(Employee::getEmpid, Function.identity()));

        // Update existing and insert new employees
        for (Employee databaseEmployee : databaseEmployees) {
            Employee excelEmployee = excelEmployeeMap.get(databaseEmployee.getEmpid());
            if (excelEmployee != null) {
                // Update existing employee
                updateEmployee(databaseEmployee.getEmpid(), excelEmployee);
                excelEmployeeMap.remove(databaseEmployee.getEmpid()); // Remove from map for deletion check
            } else {
                // Employee not found in Excel, delete it from database
                deleteEmployee(databaseEmployee.getEmpid());
            }
        }

        // Insert new employees
        for (Employee excelEmployee : excelEmployeeMap.values()) {
            createEmployee(excelEmployee);
        }

        // Return the synchronized list of employees
        return getAllEmployees();
    }
}





