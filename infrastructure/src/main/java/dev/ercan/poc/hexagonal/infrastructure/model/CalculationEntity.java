package dev.ercan.poc.hexagonal.infrastructure.model;

import dev.ercan.poc.hexagonal.calculator.domain.model.CalculationType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "calculation")
public class CalculationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private CalculationType type;
  private double operand1;
  private double operand2;
  private double result;
  private LocalDateTime timestamp;


}
