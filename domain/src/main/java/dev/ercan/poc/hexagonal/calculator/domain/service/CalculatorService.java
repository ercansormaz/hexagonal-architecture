package dev.ercan.poc.hexagonal.calculator.domain.service;

import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;
import dev.ercan.poc.hexagonal.calculator.domain.model.CalculationType;
import java.time.LocalDateTime;

public class CalculatorService {

  public Calculation calculate(double operand1, double operand2, CalculationType type) {
    double result = switch (type) {
      case ADD -> operand1 + operand2;
      case SUBTRACT -> operand1 - operand2;
      case MULTIPLY -> operand1 * operand2;
      case DIVIDE -> operand1 / operand2;
    };

    return new Calculation(type, operand1, operand2, result, LocalDateTime.now());
  }

}
