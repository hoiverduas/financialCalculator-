package com.example.financialCalculator.controller;

import com.example.financialCalculator.dto.userDto.LoginRequest;
import com.example.financialCalculator.service.imple.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        Boolean isValid = false;

        // Verificar si el valor ingresado es un correo electrónico o un nombre de usuario
        if (loginRequest.getPassword() != null) {
            if (isEmail(loginRequest.getUsername())) {
                // Si es un correo, validar con email y contraseña
                isValid = userService.longinPasswordAndEmail(loginRequest.getUsername(), loginRequest.getPassword());
            } else {
                // Si no es un correo, validar con username y contraseña
                isValid = userService.loginPasswordAndUsername(loginRequest.getUsername(), loginRequest.getPassword());
            }
        }

        if (isValid) {
            return ResponseEntity.ok("Login successful");
        } else {
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
