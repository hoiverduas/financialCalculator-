package com.example.financialCalculator.dto.userDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseLiquidationUserDTO {

    private Long idLiquidation;
    private Integer dayWorking;
    private Integer remainingVacationDays;
    private Float compensation;
    private Float Bonus;
    private Float salaryProportional;
    private Float vacationProportional;
    private Float liquidationProportional;
    private LocalDate dateLiquidation;

}
