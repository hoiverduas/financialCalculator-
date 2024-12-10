package com.example.financialCalculator.dto.userDto;

import com.example.financialCalculator.entities.User;
import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
