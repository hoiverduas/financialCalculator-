package com.example.financialCalculator.service.imple;

import com.example.financialCalculator.entities.Employee;
import com.example.financialCalculator.repository.IEmployeeRepository;
import com.example.financialCalculator.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return (List<Employee>) this.employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        Optional<Employee>optionalEmployee = employeeRepository.findById(employee.getId());

        if (optionalEmployee.isPresent()){

            Employee existEmployee = optionalEmployee.get();

            existEmployee.setCargo(employee.getCargo());
            existEmployee.setEmail(employee.getEmail());
            existEmployee.setPassword(employee.getPassword());
            existEmployee.setSalary(employee.getSalary());
            existEmployee.setFullName(employee.getFullName());
            existEmployee.setPhone(employee.getPhone());

            return this.employeeRepository.save(existEmployee);
        }else {
            throw new RuntimeException("not found");
        }


    }

    @Override
    public void deleteEmployeeById(Long id) {
           findEmployeeById(id);
           this.employeeRepository.deleteById(id);
    }
}
