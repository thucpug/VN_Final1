package com.hdt.example_assess.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    // Generate Getters and Setters...
    private int id;
    private String name;
    private String oraganization;
    private String designation;
    private int salary;

    public Employee(int id, String name, String oraganization, String designation, int salary) {
        super();
        this.id = id;
        this.name = name;
        this.oraganization = oraganization;
        this.designation = designation;
        this.salary = salary;
    }
}