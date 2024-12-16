package com.example.financialCalculator.controller;

import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.exception.UserAlreadyExistsException;
import com.example.financialCalculator.exception.UserNotFound;
import com.example.financialCalculator.service.imple.AdminService;
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
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody @Valid Admin admin , BindingResult result) throws UserAlreadyExistsException {

        if (result.hasErrors()) {
            // Aquí es donde lanzarías tu excepción de validación personalizada
            throw new ValidationException("Datos inválidos: " + result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
        }

        return ResponseEntity
                .status(HttpStatus.OK).
                body(this.adminService.createAdmin(admin));
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmin(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.adminService.findAllAdmin());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) throws UserNotFound {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.adminService.findAdminById(id));
    }
    @PutMapping
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.adminService.updateAdmin(admin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable Long id) throws UserNotFound {
        this.adminService.deleteAdminById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
