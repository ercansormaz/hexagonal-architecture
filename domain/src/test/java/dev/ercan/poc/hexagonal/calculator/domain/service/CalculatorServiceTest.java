package dev.ercan.poc.hexagonal.calculator.domain.service;

import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;
import dev.ercan.poc.hexagonal.calculator.domain.model.CalculationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

  private final CalculatorService service = new CalculatorService();
  private static final double EPS = 1e-9;

  @Test
  void calculate_addition() {
    Calculation c = service.calculate(2.5, 1.5, CalculationType.ADD);
    assertNotNull(c);
    assertEquals(CalculationType.ADD, c.getType());
    assertEquals(2.5, c.getOperand1(), EPS);
    assertEquals(1.5, c.getOperand2(), EPS);
    assertEquals(4.0, c.getResult(), EPS);
    assertNotNull(c.getTimestamp());
  }

  @Test
  void calculate_subtraction() {
    Calculation c = service.calculate(5.0, 3.0, CalculationType.SUBTRACT);
    assertEquals(CalculationType.SUBTRACT, c.getType());
    assertEquals(2.0, c.getResult(), EPS);
  }

  @Test
  void calculate_multiplication() {
    Calculation c = service.calculate(4.0, 2.5, CalculationType.MULTIPLY);
    assertEquals(CalculationType.MULTIPLY, c.getType());
    assertEquals(10.0, c.getResult(), EPS);
  }

  @Test
  void calculate_division() {
    Calculation c = service.calculate(9.0, 3.0, CalculationType.DIVIDE);
    assertEquals(CalculationType.DIVIDE, c.getType());
    assertEquals(3.0, c.getResult(), EPS);
  }

  @Test
  void calculate_division_by_zero_returns_infinity() {
    Calculation c = service.calculate(1.0, 0.0, CalculationType.DIVIDE);
    assertEquals(CalculationType.DIVIDE, c.getType());
    assertTrue(Double.isInfinite(c.getResult()));
  }
}