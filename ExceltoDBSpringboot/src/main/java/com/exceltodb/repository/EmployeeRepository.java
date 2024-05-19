
package com.exceltodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exceltodb.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
