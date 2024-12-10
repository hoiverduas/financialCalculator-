package com.example.financialCalculator.controller;

import com.example.financialCalculator.dto.liquidationDto.RequestLiquidationDTO;
import com.example.financialCalculator.dto.liquidationDto.ResponseLiquidationDTO;
import com.example.financialCalculator.service.imple.LiquidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/liquidation")
public class LiquidationController {

    private final LiquidationService liquidationService;

    public LiquidationController(LiquidationService liquidationService) {
        this.liquidationService = liquidationService;
    }

    @PostMapping
    public ResponseEntity<ResponseLiquidationDTO> createLiquidation(@RequestBody RequestLiquidationDTO requestLiquidationDTO){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.liquidationService.createLiquidation(requestLiquidationDTO));
    }
}
