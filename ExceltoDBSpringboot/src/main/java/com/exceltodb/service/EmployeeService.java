/*
package com.exceltodb.service;

import java.util.List;
import com.exceltodb.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(String empid);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(String empid, Employee employee);
    void deleteEmployee(String empid);
    List<Employee> readDataFromExcel(String excelFilePath);
}
*/
///////////////////////////////////////////////////////////
package com.exceltodb.service;

import java.util.List;
import com.exceltodb.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(String empid);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(String empid, Employee employee);
    void deleteEmployee(String empid);
    List<Employee> readDataFromExcel(String excelFilePath);
    List<Employee> synchronizeWithExcel(String excelFilePath);
}


