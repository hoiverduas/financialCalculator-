package com.example.financialCalculator.service;

import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.entities.User;

import java.util.List;

public interface IAdminService {

    Admin createAdmin(Admin admin);
    List<Admin> findAllAdmin();
    Admin findAdminById(Long id);
    Admin updateAdmin(Admin admin);
    void deleteAdminById(Long id);
    Boolean longinPasswordAndEmail(String password, String email);
    Boolean loginPasswordAndUsername(String password, String username);
}
