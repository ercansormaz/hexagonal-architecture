package dev.ercan.poc.hexagonal.calculator.domain.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Calculation {

  private final CalculationType type;
  private final double operand1;
  private final double operand2;
  private final double result;
  private final LocalDateTime timestamp;

}