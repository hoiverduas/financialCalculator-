package com.example.financialCalculator.dto.liquidationDto;

import lombok.Data;

@Data
public class ResponseLiquidationDTO {

    private Long id;
    private Integer dayWorking;
    private Integer remainingVacationDays;
    private Double compensation;
    private Double Bonus;
    private Long userId;
    private Double salaryProportional;
    private Double vacationProportional;
    private Double liquidationProportional;
}
