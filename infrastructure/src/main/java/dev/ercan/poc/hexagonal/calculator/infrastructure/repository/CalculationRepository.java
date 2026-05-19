package dev.ercan.poc.hexagonal.calculator.infrastructure.repository;

import dev.ercan.poc.hexagonal.calculator.infrastructure.model.CalculationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<CalculationEntity, Long> {

}
