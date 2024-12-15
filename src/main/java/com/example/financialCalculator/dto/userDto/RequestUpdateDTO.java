package com.example.financialCalculator.dto.userDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestUpdateDTO {

    private Long id;
    private String fullName;
    private String phone;
    private String cargo;
    private Float salary;
    private String numIdentification;
    private LocalDate dateStart;

}
