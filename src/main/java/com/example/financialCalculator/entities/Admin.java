package com.example.financialCalculator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "admins")
@DiscriminatorValue("ADMIN")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends User{

    @Enumerated(EnumType.STRING)
    private AreaOfResponsibility areaOfResponsibility;



}