package dev.ercan.poc.hexagonal.infrastructure.repository;

import dev.ercan.poc.hexagonal.infrastructure.model.CalculationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<CalculationEntity, Long> {

}
