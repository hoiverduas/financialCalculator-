package com.example.financialCalculator.dto.liquidationDto;


import lombok.Data;

@Data
public class RequestLiquidationDTO {

    private Integer dayWorking;
    private Integer remainingVacationDays;
    private Double compensation;
    private Double Bonus;
    private Long userId;

}
