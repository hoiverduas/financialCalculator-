package com.example.financialCalculator.dto.userDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RequestEmployeeDTO {

    private String fullName;
    private String phone;
    private String cargo;
    private Float salary;
    private LocalDate dateStart;

}
