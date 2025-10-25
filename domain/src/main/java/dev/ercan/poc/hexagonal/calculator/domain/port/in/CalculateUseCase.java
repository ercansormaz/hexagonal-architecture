package dev.ercan.poc.hexagonal.calculator.domain.port.in;

import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;

public interface CalculateUseCase {

  Calculation add(double a, double b);
  Calculation subtract(double a, double b);
  Calculation multiply(double a, double b);
  Calculation divide(double a, double b);

}
