package com.example.financialCalculator.controller;

import com.example.financialCalculator.dto.liquidationDto.RequestLiquidationDTO;
import com.example.financialCalculator.dto.liquidationDto.ResponseLiquidationDTO;
import com.example.financialCalculator.exception.UserNotFound;
import com.example.financialCalculator.service.imple.LiquidationService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/liquidation")
public class LiquidationController {

    private final LiquidationService liquidationService;

    public LiquidationController(LiquidationService liquidationService) {
        this.liquidationService = liquidationService;
    }

    @PostMapping
    public ResponseEntity<ResponseLiquidationDTO> createLiquidation(@RequestBody @Valid RequestLiquidationDTO requestLiquidationDTO, BindingResult result) throws UserNotFound {

        if (result.hasErrors()) {
            // Aquí es donde lanzarías tu excepción de validación personalizada
            throw new ValidationException("Datos inválidos: " + result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.liquidationService.createLiquidation(requestLiquidationDTO));
    }
}
