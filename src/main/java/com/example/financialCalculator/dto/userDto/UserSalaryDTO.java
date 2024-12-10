package com.example.financialCalculator.dto.userDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserSalaryDTO {
    private String fullName;
    private BigDecimal salary;
}
