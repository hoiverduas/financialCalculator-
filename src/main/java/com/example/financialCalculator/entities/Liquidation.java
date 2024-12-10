package com.example.financialCalculator.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Double compensation;
    private Double Bonus;
    private Double salaryProportional;
    private Double vacationProportional;
    private Double liquidationProportional;

    @ManyToOne
    private Employee employee;

}
