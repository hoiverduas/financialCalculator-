package com.example.financialCalculator.service;

import com.example.financialCalculator.dto.liquidationDto.RequestLiquidationDTO;
import com.example.financialCalculator.dto.liquidationDto.ResponseLiquidationDTO;

public interface ILiquidationService {

    ResponseLiquidationDTO createLiquidation(RequestLiquidationDTO requestLiquidationDTO);
}
