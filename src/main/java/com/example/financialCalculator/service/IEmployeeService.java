package com.example.financialCalculator.service;

import com.example.financialCalculator.dto.userDto.RequestUpdateDTO;
import com.example.financialCalculator.dto.userDto.ResponseEmployeeDTO;
import com.example.financialCalculator.entities.Employee;
import com.example.financialCalculator.exception.UserAlreadyExistsException;
import com.example.financialCalculator.exception.UserNotFound;

import java.util.List;

public interface IEmployeeService {

    Employee createEmployee(Employee employee) throws UserAlreadyExistsException;
    List<ResponseEmployeeDTO> findAllEmployee();
    Employee findEmployeeById(Long id) throws UserNotFound;
    ResponseEmployeeDTO updateEmployee(RequestUpdateDTO requestUpdateDTO);
    void deleteEmployeeById(Long id) throws UserNotFound;
}
