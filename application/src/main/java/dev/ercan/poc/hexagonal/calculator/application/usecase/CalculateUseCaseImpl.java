package dev.ercan.poc.hexagonal.calculator.application.usecase;

import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;
import dev.ercan.poc.hexagonal.calculator.domain.model.CalculationType;
import dev.ercan.poc.hexagonal.calculator.domain.port.in.CalculateUseCase;
import dev.ercan.poc.hexagonal.calculator.domain.port.out.CalculationLogger;
import dev.ercan.poc.hexagonal.calculator.domain.service.CalculatorService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalculateUseCaseImpl implements CalculateUseCase {

  private final CalculatorService calculatorService;
  private final CalculationLogger calculationLogger;

  @Override
  public Calculation add(double a, double b) {
    Calculation calculation = calculatorService.calculate(a, b, CalculationType.ADD);
    calculationLogger.log(calculation);
    return calculation;
  }

  @Override
  public Calculation subtract(double a, double b) {
    Calculation calculation = calculatorService.calculate(a, b, CalculationType.SUBTRACT);
    calculationLogger.log(calculation);
    return calculation;
  }

  @Override
  public Calculation multiply(double a, double b) {
    Calculation calculation = calculatorService.calculate(a, b, CalculationType.MULTIPLY);
    calculationLogger.log(calculation);
    return calculation;
  }

  @Override
  public Calculation divide(double a, double b) {
    Calculation calculation = calculatorService.calculate(a, b, CalculationType.DIVIDE);
    calculationLogger.log(calculation);
    return calculation;
  }

}
