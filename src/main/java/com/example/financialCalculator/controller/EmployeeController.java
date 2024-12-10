package com.example.financialCalculator.controller;

import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.entities.Employee;
import com.example.financialCalculator.service.imple.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;



    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<Employee> createAdmin(@RequestBody Employee employee){
        return ResponseEntity
                .status(HttpStatus.OK).
                body(this.employeeService.createEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllAdmin(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.employeeService.findAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getAdminById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.employeeService.findEmployeeById(id));
    }
    @PutMapping
    public ResponseEntity<Employee> updateAdmin(@RequestBody Employee employee){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.employeeService.updateEmployee(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable Long id){
        this.employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
