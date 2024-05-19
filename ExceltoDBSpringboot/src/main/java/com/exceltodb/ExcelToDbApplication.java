/*

package com.exceltodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import com.exceltodb.service.EmployeeService;
import com.exceltodb.model.Employee;
import java.util.List;

@SpringBootApplication
public class ExcelToDbApplication {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(ExcelToDbApplication.class, args);
    }

    @PostConstruct
    public void init() {
        String excelFilePath = "C:\\Users\\bojreshm\\OneDrive - Publicis Groupe\\Desktop\\Data.xlsx";
        List<Employee> employees = employeeService.readDataFromExcel(excelFilePath);
        for (Employee employee : employees) {
            employeeService.createEmployee(employee);
        }
    }
}
*/
package com.exceltodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import com.exceltodb.service.EmployeeService;
import com.exceltodb.model.Employee;
import java.util.List;

@SpringBootApplication
public class ExcelToDbApplication {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(ExcelToDbApplication.class, args);
    }

    @PostConstruct
    public void init() {
        String excelFilePath = "C:\\Users\\bojreshm\\OneDrive - Publicis Groupe\\Desktop\\Data.xlsx";
        List<Employee> employeesFromExcel = employeeService.readDataFromExcel(excelFilePath);
        List<Employee> employeesFromDatabase = employeeService.getAllEmployees();

        // Update existing employees and insert new ones
        for (Employee excelEmployee : employeesFromExcel) {
            boolean found = false;
            for (Employee dbEmployee : employeesFromDatabase) {
                if (excelEmployee.getEmpid().equals(dbEmployee.getEmpid())) {
                    // Update existing employee
                    employeeService.updateEmployee(dbEmployee.getEmpid(), excelEmployee);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Insert new employee
                employeeService.createEmployee(excelEmployee);
            }
        }

        // Delete employees not present in the Excel file
        for (Employee dbEmployee : employeesFromDatabase) {
            boolean found = false;
            for (Employee excelEmployee : employeesFromExcel) {
                if (excelEmployee.getEmpid().equals(dbEmployee.getEmpid())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Delete employee from the database
                employeeService.deleteEmployee(dbEmployee.getEmpid());
            }
        }
    }
}

