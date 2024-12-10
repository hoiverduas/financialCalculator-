package com.example.financialCalculator.repository;

import com.example.financialCalculator.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User,Long> {

    Boolean existsByEmailAndPassword(String email, String password);

    Boolean existsByUsernameAndPassword(String username, String password);

}
