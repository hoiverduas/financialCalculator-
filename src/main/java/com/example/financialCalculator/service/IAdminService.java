package com.example.financialCalculator.service;

import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.entities.User;
import com.example.financialCalculator.exception.UserAlreadyExistsException;
import com.example.financialCalculator.exception.UserNotFound;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IAdminService {

    Admin createAdmin(Admin admin) throws UserAlreadyExistsException;
    List<Admin> findAllAdmin();
    Admin findAdminById(Long id) throws UserNotFound;
    Admin updateAdmin(Admin admin);
    void deleteAdminById(Long id) throws UserNotFound;
    Boolean longinPasswordAndEmail(String password, String email);
    Boolean loginPasswordAndUsername(String password, String username);
}
