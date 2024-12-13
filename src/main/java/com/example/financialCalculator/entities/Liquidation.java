package com.example.financialCalculator.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "liquidations")
public class Liquidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dayWorking;
    private Integer remainingVacationDays;
    private LocalDate dateLiquidation;
    private Float compensation;
    private Float Bonus;
    private Float salaryProportional;
    private Float vacationProportional;
    private Float liquidationProportional;


    @ManyToOne
    private Employee employee;

}
