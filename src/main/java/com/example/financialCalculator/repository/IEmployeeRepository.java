package com.example.financialCalculator.repository;

import com.example.financialCalculator.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEmployeeRepository extends CrudRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.liquidations")
    List<Employee> findAllWithLiquidation();

}
