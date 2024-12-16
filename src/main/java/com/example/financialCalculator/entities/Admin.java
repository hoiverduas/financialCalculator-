package com.example.financialCalculator.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El campo no puede estar vacío")
    private String username;
    @NotBlank(message = "El campo no puede estar vacío")
    private String email;
    @NotBlank(message = "El campo no puede estar vacío")
    private String password;
    @Enumerated(EnumType.STRING)
    private AreaOfResponsibility areaOfResponsibility;



}
