package com.example.financialCalculator.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Table(name = "employees")
@DiscriminatorValue("EMPLOYEE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends User{

    @NotBlank(message = "El campo no puede estar vacío")
    private String cargo;

    private LocalDate dateStart;

    @NotNull(message = "La cantidad no puede ser nula")
    @Positive(message = "La cantidad debe ser un número positivo")
    private Float salary;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    List<Liquidation> liquidations = new ArrayList<>();
}
