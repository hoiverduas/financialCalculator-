package com.example.financialCalculator.dto.liquidationDto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestLiquidationDTO {

    private Integer dayWorking;
    private Integer remainingVacationDays;
    private Float compensation;
    private Float Bonus;
    private LocalDate dateLiquidation;
    private Long userId;

}
