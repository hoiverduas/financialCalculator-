package com.example.financialCalculator.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@DiscriminatorColumn(name = "roles")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El campo no puede estar vacío")
    private String fullName;
    @NotBlank(message = "El campo no puede estar vacío")
    private String phone;
    @NotBlank(message = "El campo no puede estar vacío")
    private String numIdentification;


}
