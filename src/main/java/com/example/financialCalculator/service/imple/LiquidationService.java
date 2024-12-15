package com.example.financialCalculator.service.imple;

import com.example.financialCalculator.dto.liquidationDto.RequestLiquidationDTO;
import com.example.financialCalculator.dto.liquidationDto.ResponseLiquidationDTO;
import com.example.financialCalculator.entities.Employee;
import com.example.financialCalculator.entities.Liquidation;
import com.example.financialCalculator.exception.UserNotFound;
import com.example.financialCalculator.repository.IEmployeeRepository;
import com.example.financialCalculator.repository.ILiquidationRepository;
import com.example.financialCalculator.service.ILiquidationService;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
public class LiquidationService implements ILiquidationService {

    private final ILiquidationRepository liquidationRepository;
    private final IEmployeeRepository employeeRepository;

    public LiquidationService(ILiquidationRepository liquidationRepository, IEmployeeRepository employeeRepository) {
        this.liquidationRepository = liquidationRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public ResponseLiquidationDTO createLiquidation(RequestLiquidationDTO requestLiquidationDTO) throws UserNotFound {

            Long userId = requestLiquidationDTO.getUserId();
            Employee employee =  employeeRepository.findById(userId).orElseThrow(()->new UserNotFound("not found"));

            Liquidation liquidation = mapToEntity(requestLiquidationDTO);
            liquidation.setEmployee(employee);

        Float proportionalSalary = salaryProportional(employee.getSalary(),liquidation.getDayWorking());
        Float proportionalVacation = vacationProportional(liquidation.getEmployee().getSalary(),liquidation.getRemainingVacationDays());
        Float bonus = requestLiquidationDTO.getBonus();
        Float compensation = requestLiquidationDTO.getCompensation();

        liquidation.setLiquidationProportional(proportionalSalary);
        liquidation.setVacationProportional(proportionalVacation);
        liquidation.setSalaryProportional(proportionalSalary);

        Float liquidationTotal = proportionalSalary + proportionalVacation + bonus + compensation;

        liquidation.setLiquidationProportional(liquidationTotal);
        liquidation.setDateLiquidation(LocalDate.now());
            this.liquidationRepository.save(liquidation);

            ResponseLiquidationDTO responseLiquidationDTO = new ResponseLiquidationDTO();


        return mapToDto(liquidation);
    }


    private ResponseLiquidationDTO mapToDto(Liquidation liquidation){

        ResponseLiquidationDTO responseLiquidationDTO = new ResponseLiquidationDTO();
        responseLiquidationDTO.setIdLiquidation(liquidation.getId());
        responseLiquidationDTO.setBonus(liquidation.getBonus());
        responseLiquidationDTO.setCompensation(liquidation.getCompensation());
        responseLiquidationDTO.setDayWorking(liquidation.getDayWorking());
        responseLiquidationDTO.setRemainingVacationDays(liquidation.getRemainingVacationDays());
        responseLiquidationDTO.setUserId(liquidation.getId());
        responseLiquidationDTO.setLiquidationProportional(liquidation.getLiquidationProportional());
        responseLiquidationDTO.setVacationProportional(liquidation.getVacationProportional());
        responseLiquidationDTO.setSalaryProportional(liquidation.getSalaryProportional());

        return responseLiquidationDTO;
    }

    private Liquidation mapToEntity(RequestLiquidationDTO requestLiquidationDTO){

        Liquidation liquidation = new Liquidation();

        liquidation.setBonus(requestLiquidationDTO.getBonus());
        liquidation.setCompensation(requestLiquidationDTO.getCompensation());
        liquidation.setDayWorking(requestLiquidationDTO.getDayWorking());
        liquidation.setRemainingVacationDays(requestLiquidationDTO.getRemainingVacationDays());

        return liquidation;

    }


    private Float salaryProportional(Float salary,Integer dayWorking){
        Float calculateSalary = (salary/30)*dayWorking;
        return calculateSalary;

    }

    private Float vacationProportional(Float salary,Integer pendingVacationDays){
        Float calculateVacation = (salary/30)*pendingVacationDays;
        return calculateVacation;

    }

}
