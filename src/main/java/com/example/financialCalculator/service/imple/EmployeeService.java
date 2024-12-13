package com.example.financialCalculator.service.imple;

import com.example.financialCalculator.dto.userDto.ResponseEmployeeDTO;
import com.example.financialCalculator.dto.userDto.ResponseLiquidationDTO;
import com.example.financialCalculator.entities.Employee;
import com.example.financialCalculator.entities.Liquidation;
import com.example.financialCalculator.repository.IEmployeeRepository;
import com.example.financialCalculator.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setDateStart(LocalDate.now());
        return this.employeeRepository.save(employee);
    }

    @Override
    public List<ResponseEmployeeDTO> findAllEmployee() {

        List<Employee> employees = employeeRepository.findAllWithLiquidation();
        return employees.stream()
                .map(this::mapToResponseEmployeeDTO)
                .collect(Collectors.toList());
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

    private ResponseEmployeeDTO mapToResponseEmployeeDTO(Employee employee) {

        ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO();

        responseEmployeeDTO.setId(employee.getId());
        responseEmployeeDTO.setCargo(employee.getCargo());
        responseEmployeeDTO.setFullName(employee.getFullName());
        responseEmployeeDTO.setSalary(employee.getSalary());
        responseEmployeeDTO.setPhone(employee.getPhone());
        responseEmployeeDTO.setDateStart(employee.getDateStart());
        responseEmployeeDTO.setLiquidationDTOS(employee.getLiquidations().stream()
                .map(this::mapToResponseLiquidationDTO)
                .collect(Collectors.toList()));

        return responseEmployeeDTO;
    }

    private ResponseLiquidationDTO mapToResponseLiquidationDTO(Liquidation liquidation) {

        ResponseLiquidationDTO responseLiquidationDTO = new ResponseLiquidationDTO();

        responseLiquidationDTO.setId(liquidation.getId());
        responseLiquidationDTO.setBonus(liquidation.getBonus());
        responseLiquidationDTO.setCompensation(liquidation.getCompensation());
        responseLiquidationDTO.setLiquidationProportional(liquidation.getLiquidationProportional());
        responseLiquidationDTO.setDayWorking(liquidation.getDayWorking());
        responseLiquidationDTO.setVacationProportional(liquidation.getVacationProportional());
        responseLiquidationDTO.setRemainingVacationDays(liquidation.getRemainingVacationDays());
        responseLiquidationDTO.setSalaryProportional(liquidation.getSalaryProportional());
        responseLiquidationDTO.setDateLiquidation(liquidation.getDateLiquidation());
        return responseLiquidationDTO;
    }



}
