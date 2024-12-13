package com.example.financialCalculator.service;

import com.example.financialCalculator.dto.userDto.ResponseEmployeeDTO;
import com.example.financialCalculator.entities.Admin;
import com.example.financialCalculator.entities.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee createEmployee(Employee employee);
    List<ResponseEmployeeDTO> findAllEmployee();
    Employee findEmployeeById(Long id);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Long id);
}
