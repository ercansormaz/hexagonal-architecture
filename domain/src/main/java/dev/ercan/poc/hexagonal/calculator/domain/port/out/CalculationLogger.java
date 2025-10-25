package dev.ercan.poc.hexagonal.calculator.domain.port.out;

import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;

public interface CalculationLogger {

  void log(Calculation calculation);

}
