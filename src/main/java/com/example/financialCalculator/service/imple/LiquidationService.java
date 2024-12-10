package com.example.financialCalculator.service.imple;

import com.example.financialCalculator.dto.liquidationDto.RequestLiquidationDTO;
import com.example.financialCalculator.dto.liquidationDto.ResponseLiquidationDTO;
import com.example.financialCalculator.entities.Employee;
import com.example.financialCalculator.entities.Liquidation;
import com.example.financialCalculator.repository.IEmployeeRepository;
import com.example.financialCalculator.repository.ILiquidationRepository;
import com.example.financialCalculator.service.ILiquidationService;
import org.springframework.stereotype.Service;

@Service
public class LiquidationService implements ILiquidationService {

    private final ILiquidationRepository liquidationRepository;
    private final IEmployeeRepository employeeRepository;

    public LiquidationService(ILiquidationRepository liquidationRepository, IEmployeeRepository employeeRepository) {
        this.liquidationRepository = liquidationRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public ResponseLiquidationDTO createLiquidation(RequestLiquidationDTO requestLiquidationDTO) {

            Long userId = requestLiquidationDTO.getUserId();
            Employee employee =  employeeRepository.findById(userId).orElseThrow(()->new RuntimeException("not found"));

            Liquidation liquidation = mapToEntity(requestLiquidationDTO);
            liquidation.setEmployee(employee);

        Double proportionalSalary = salaryProportional(employee.getSalary(),liquidation.getDayWorking());
        Double proportionalVacation = vacationProportional(liquidation.getEmployee().getSalary(),liquidation.getRemainingVacationDays());
        Double bonus = requestLiquidationDTO.getBonus();
        Double compensation = requestLiquidationDTO.getCompensation();

        liquidation.setLiquidationProportional(proportionalSalary);
        liquidation.setVacationProportional(proportionalVacation);
        liquidation.setSalaryProportional(proportionalSalary);

        Double liquidationTotal = proportionalSalary + proportionalVacation + bonus + compensation;

        liquidation.setLiquidationProportional(liquidationTotal);

            this.liquidationRepository.save(liquidation);

            ResponseLiquidationDTO responseLiquidationDTO = new ResponseLiquidationDTO();

        return mapToDto(liquidation);
    }


    private ResponseLiquidationDTO mapToDto(Liquidation liquidation){

        ResponseLiquidationDTO responseLiquidationDTO = new ResponseLiquidationDTO();
        responseLiquidationDTO.setId(liquidation.getId());
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


    private Double salaryProportional(Double salary,Integer dayWorking){
        Double calculateSalary = (salary/30)*dayWorking;
        return calculateSalary;

    }

    private Double vacationProportional(Double salary,Integer pendingVacationDays){
        Double calculateVacation = (salary/30)*pendingVacationDays;
        return calculateVacation;

    }

}
