package com.example.financialCalculator.service;

import com.example.financialCalculator.dto.liquidationDto.RequestLiquidationDTO;
import com.example.financialCalculator.dto.liquidationDto.ResponseLiquidationDTO;
import com.example.financialCalculator.exception.UserNotFound;

public interface ILiquidationService {

    ResponseLiquidationDTO createLiquidation(RequestLiquidationDTO requestLiquidationDTO) throws UserNotFound;
}
