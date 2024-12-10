package com.example.financialCalculator.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Table(name = "employees")
@DiscriminatorValue("EMPLOYEE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends User{

    private String cargo;
    private Double salary;
    @OneToMany(mappedBy = "employee")
    List<Liquidation> liquidations = new ArrayList<>();
}
