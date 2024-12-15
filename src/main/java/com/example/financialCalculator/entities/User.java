package com.example.financialCalculator.entities;


import jakarta.persistence.*;
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
    private String fullName;
    private String phone;
    private String numIdentification;





}
