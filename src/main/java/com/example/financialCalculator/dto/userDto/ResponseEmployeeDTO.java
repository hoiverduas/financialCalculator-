package com.example.financialCalculator.dto.userDto;

import lombok.Data;


import java.time.LocalDate;
import java.util.List;
@Data
public class ResponseEmployeeDTO {

    private Long id;
    private String fullName;
    private String phone;
    private String cargo;
    private String numIdentification;
    private Float salary;
    private LocalDate dateStart;
    List<ResponseLiquidationUserDTO> liquidationDTOS ;
}
