package com.example.financialCalculator.controller;

import com.example.financialCalculator.dto.userDto.LoginRequestDTO;
import com.example.financialCalculator.service.imple.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
//@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AdminService adminService;

    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {

        Boolean isValid = false;

        // Verificar si el valor ingresado es un correo electrónico o un nombre de usuario
        if (loginRequest.getPassword() != null) {
            if (isEmail(loginRequest.getUsername())) {
                isValid = adminService.longinPasswordAndEmail(loginRequest.getUsername(), loginRequest.getPassword());
            } else {
                isValid = adminService.loginPasswordAndUsername(loginRequest.getUsername(), loginRequest.getPassword());
            }
        }

        if (isValid) {
            System.out.println("loginRequest = " + loginRequest);
            System.out.println("isValid = " + isValid);
            return ResponseEntity.ok("Login successful");


        } else {
            System.out.println("loginRequest = " + loginRequest);
            System.out.println("isValid = " + isValid);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // Método para verificar si el valor es un correo electrónico
    private boolean isEmail(String username) {
        // Expresión regular para validar formato de correo electrónico
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return username.matches(emailRegex);
    }



}
