package com.example.financialCalculator.repository;

import com.example.financialCalculator.entities.Liquidation;
import org.springframework.data.repository.CrudRepository;

public interface ILiquidationRepository extends CrudRepository<Liquidation,Long> {
}
