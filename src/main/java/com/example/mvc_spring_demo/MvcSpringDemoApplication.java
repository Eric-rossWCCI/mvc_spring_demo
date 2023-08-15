package com.example.mvc_spring_demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mvc_spring_demo.models.Employee;
import com.example.mvc_spring_demo.repositories.EmployeeRepository;

@SpringBootApplication
public class MvcSpringDemoApplication {
	// Autowired dependency allows us to utilize this dependency using spring
	// dependency injection
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MvcSpringDemoApplication.class, args);
	}

	
	@Bean
	public String running() {
		// created the entity to save to the table
		Employee employee = new Employee("Eric");
		Employee employee2 = new Employee("Milo");
		Employee employee3 = new Employee("Luna");
		System.out.println("Here is the count before saving " + employeeRepository.count());
		// equivalent to insert into employee ([columns]), values ([values]);
		employeeRepository.save(employee);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
		// select * from Employee where name = 'Milo';
		List<Employee> result = employeeRepository.findByName("Milo");
		System.out.println("Here is the count after saving " + employeeRepository.count());
		// factory repository method to delete records by id
		employeeRepository.deleteById(1L);
		// equivalent select * from employee;
		System.out.println("Actual contents of table "+ employeeRepository.findAll());
		/**
		 *  update existing row
		 * Grab entity by id
		 * update entity
		 * save entity
		**/
		Optional<Employee> foundById = employeeRepository.findById(2L);
		if(foundById.isPresent()){
			Employee emp = foundById.get();
			emp.setName("Scoob");
			employeeRepository.save(emp);
		}
		
		return "running";
	}

}
