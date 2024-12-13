package com.example.financialCalculator.service;

import com.example.financialCalculator.entities.User;

import java.util.List;

public interface IUserService {

    User createUser(User user);
    List<User> findAllUser();
    User findUserById(Long id);
    User updateUser(User user);
    void deleteUserById(Long id);

}
