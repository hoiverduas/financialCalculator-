package com.example.financialCalculator.controller;

import com.example.financialCalculator.dto.userDto.RequestUpdateDTO;
import com.example.financialCalculator.dto.userDto.ResponseEmployeeDTO;
import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.entities.Employee;
import com.example.financialCalculator.exception.UserAlreadyExistsException;
import com.example.financialCalculator.exception.UserNotFound;
import com.example.financialCalculator.service.imple.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
//@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService employeeService;



    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<Employee> createAdmin(@RequestBody @Valid Employee employee, BindingResult result) throws UserAlreadyExistsException {

        if (result.hasErrors()) {
            // Aquí es donde lanzarías tu excepción de validación personalizada
            throw new ValidationException("Datos inválidos: " + result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
        }


        return ResponseEntity
                .status(HttpStatus.OK).
                body(this.employeeService.createEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<ResponseEmployeeDTO>> getAllAdmin(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.employeeService.findAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getAdminById(@PathVariable Long id) throws UserNotFound {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.employeeService.findEmployeeById(id));
    }
    @PutMapping
    public ResponseEntity<ResponseEmployeeDTO> updateAdmin(@RequestBody RequestUpdateDTO requestUpdateDTO){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.employeeService.updateEmployee(requestUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable Long id) throws UserNotFound {
        this.employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
