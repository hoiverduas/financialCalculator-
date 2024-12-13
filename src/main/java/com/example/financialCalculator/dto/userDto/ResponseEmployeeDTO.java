package com.example.financialCalculator.dto.userDto;

import com.example.financialCalculator.entities.Liquidation;
import jakarta.persistence.OneToMany;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class ResponseEmployeeDTO {

    private Long id;
    private String fullName;
    private String phone;
    private String cargo;
    private Float salary;
    private LocalDate dateStart;
    List<ResponseLiquidationDTO> liquidationDTOS ;
}
