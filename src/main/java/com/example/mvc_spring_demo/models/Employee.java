package com.example.mvc_spring_demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// This annotation is designated this class as a blueprint for the Employee table
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    
    // need to have a default constructor around so that JPA can do it's thing
    public Employee() {
    }
    // create a controller that doesn't include the generated Id because it is being generated for us
    public Employee( String name) {
        this.name = name;
    }
    // make sure getters are formatted properly so that thymeleaf template can use them to harvest values
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }
}
