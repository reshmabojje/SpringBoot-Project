package com.exceltodb.model;

import javax.persistence.Entity;
import javax.persistence.Id;
//javax.persistence is part of spring data jpa

@Entity
public class Employee {
    @Id
    private String empid;
    private String firstName;
    private String lastName;
    private String designation;
    private String project;
    private String status;
    
    public Employee() {
        // Default constructor required by JPA
    }
    
    public Employee(String empid, String firstName, String lastName, String designation, String project, String status) {
        this.empid = empid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.project = project;
        this.status = status;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empid='" + empid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", designation='" + designation + '\'' +
                ", project='" + project + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

