package com.example.financialCalculator.dto.liquidationDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ResponseLiquidationDTO {

    private Long idLiquidation;
    private Integer dayWorking;
    private Integer remainingVacationDays;
    private Float compensation;
    private Float Bonus;
    private Long userId;
    private Float salaryProportional;
    private Float vacationProportional;
    private Float liquidationProportional;
    private LocalDate dateLiquidation;
}
