package com.example.financialCalculator.repository;

import com.example.financialCalculator.entities.Admin;
import org.springframework.data.repository.CrudRepository;

public interface IAdminRepository extends CrudRepository<Admin,Long> {

    Boolean existsByEmailAndPassword(String email, String password);

    Boolean existsByUsernameAndPassword(String username, String password);


}
