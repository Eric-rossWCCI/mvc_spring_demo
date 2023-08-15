package com.example.mvc_spring_demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.mvc_spring_demo.models.Employee;
import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByName(String name);
    
}
