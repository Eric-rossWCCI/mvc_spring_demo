package com.example.mvc_spring_demo.controllers;

import java.util.Optional;

import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
// add controller annotation for spring
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mvc_spring_demo.models.Employee;
import com.example.mvc_spring_demo.repositories.EmployeeRepository;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/greeting/{name}")
    // take in pathVariable and model
    public String greeting(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/getAllEmployees")
    public String getAllEmployees(Model model) {
        /*
         * use the crud equivalent of select * from employees;
         * save that result so that it can be displayed via template html
         */

        addEmployeeModel(model);

        return "employees";
    }
    

    @PostMapping("/createNewEmployee")
    public String createEmployee(@RequestBody Employee employee, Model model) {
        /*
         * use the crud equivalent of insert into employees ....;
         * save that result so that it can be displayed via template html
         */
        employeeRepository.save(employee);
        // grab all employee records after updating the table to include a new employee
        addEmployeeModel(model);

        return "employees";
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        /*
         * use the crud equivalent of delete from employees where ....;
         */
        employeeRepository.deleteById(id);
        // grab all employee records after updating the table to include a new employee
        addEmployeeModel(model);

        return "employees";
    }

    @PutMapping("/updateEmployee/{id}/{name}")
    public String updateEmployee(@PathVariable Long id, @PathVariable String name, Model model) {
        /*
         * use the crud equivalent of UPDATE table_name
         * SET column1 = value1, column2 = value2, ...
         * WHERE condition;;
         * save that result so that it can be displayed via template html
         */
        // find entity by id if it exists
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(name);
            employeeRepository.save(employee);
        }
        // grab all employee records after updating the table to include a new employee
        addEmployeeModel(model);
        return "employees";
    }
    private void addEmployeeModel(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();

        model.addAttribute("employees", employees);

    }
}
