package com.example.financialCalculator.dto.userDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ResponseLiquidationDTO {

    private Long id;
    private Integer dayWorking;
    private Integer remainingVacationDays;
    private Float compensation;
    private Float Bonus;
    private Float salaryProportional;
    private Float vacationProportional;
    private Float liquidationProportional;
    private LocalDate dateLiquidation;

}
