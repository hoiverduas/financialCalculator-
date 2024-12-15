package com.example.financialCalculator.service.imple;

import com.example.financialCalculator.dto.userDto.RequestUpdateDTO;
import com.example.financialCalculator.dto.userDto.ResponseEmployeeDTO;
import com.example.financialCalculator.dto.userDto.ResponseLiquidationUserDTO;
import com.example.financialCalculator.entities.Employee;
import com.example.financialCalculator.entities.Liquidation;
import com.example.financialCalculator.exception.UserAlreadyExistsException;
import com.example.financialCalculator.exception.UserNotFound;
import com.example.financialCalculator.repository.IEmployeeRepository;
import com.example.financialCalculator.repository.IUserRepository;
import com.example.financialCalculator.service.IEmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IUserRepository userRepository;

    public EmployeeService(IEmployeeRepository employeeRepository, IUserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) throws UserAlreadyExistsException {

        Boolean employeeExist =  userRepository.existsByNumIdentification(employee.getNumIdentification());
         if (employeeExist){
             throw new UserAlreadyExistsException("Employee with this identification already exists");
         }else {
             employee.setDateStart(LocalDate.now());
             return this.employeeRepository.save(employee);
         }

    }

    @Override
    public List<ResponseEmployeeDTO> findAllEmployee() {

        List<Employee> employees = employeeRepository.findAllWithLiquidation();
        return employees.stream()
                .map(this::mapToResponseEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Employee findEmployeeById(Long id) throws UserNotFound {
        return this.employeeRepository.findById(id).orElseThrow(()->new UserNotFound("not found"));
    }

    @Override
    public ResponseEmployeeDTO updateEmployee(RequestUpdateDTO requestUpdateDTO) {

        Optional<Employee>optionalEmployee = employeeRepository.findById(requestUpdateDTO.getId());

        if (optionalEmployee.isPresent()){

            Employee existEmployee = optionalEmployee.get();

            existEmployee.setCargo(requestUpdateDTO.getCargo());
            existEmployee.setSalary(requestUpdateDTO.getSalary());
            existEmployee.setFullName(requestUpdateDTO.getFullName());
            existEmployee.setPhone(requestUpdateDTO.getPhone());

            this.employeeRepository.save(existEmployee);


            return mapToResponseEmployeeDTO(existEmployee);

        }else {
            throw new RuntimeException("not found");
        }


    }

    @Override
    public void deleteEmployeeById(Long id) throws UserNotFound {
           findEmployeeById(id);
           this.employeeRepository.deleteById(id);
    }

    private ResponseEmployeeDTO mapToResponseEmployeeDTO(Employee employee) {

        ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO();

        responseEmployeeDTO.setId(employee.getId());
        responseEmployeeDTO.setCargo(employee.getCargo());
        responseEmployeeDTO.setFullName(employee.getFullName());
        responseEmployeeDTO.setNumIdentification(employee.getNumIdentification());
        responseEmployeeDTO.setSalary(employee.getSalary());
        responseEmployeeDTO.setPhone(employee.getPhone());
        responseEmployeeDTO.setDateStart(employee.getDateStart());
        responseEmployeeDTO.setLiquidationDTOS(employee.getLiquidations().stream()
                .map(this::mapToResponseLiquidationDTO)
                .collect(Collectors.toList()));

        return responseEmployeeDTO;
    }

    private ResponseLiquidationUserDTO mapToResponseLiquidationDTO(Liquidation liquidation) {

        ResponseLiquidationUserDTO responseLiquidationDTO = new ResponseLiquidationUserDTO();

        responseLiquidationDTO.setIdLiquidation(liquidation.getId());
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


    private Employee mapToRequestUpdateDto(RequestUpdateDTO requestUpdateDTO) {

        Employee employee = new Employee();

        employee.setId(requestUpdateDTO.getId());
        employee.setCargo(requestUpdateDTO.getCargo());
        employee.setFullName(requestUpdateDTO.getFullName());
        employee.setSalary(requestUpdateDTO.getSalary());
        employee.setPhone(requestUpdateDTO.getPhone());
        employee.setNumIdentification(requestUpdateDTO.getNumIdentification());
        employee.setDateStart(requestUpdateDTO.getDateStart());

        return employee;
    }
}
