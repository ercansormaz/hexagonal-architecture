package dev.ercan.poc.hexagonal.adapter.soap.service;

import dev.ercan.poc.hexagonal.adapter.soap.dto.CalculationRequest;
import dev.ercan.poc.hexagonal.adapter.soap.dto.CalculationResponse;
import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;
import dev.ercan.poc.hexagonal.calculator.domain.port.in.CalculateUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorSoapServiceTest {

  private static final double EPS = 1e-9;

  @Mock
  CalculateUseCase calculateUseCase;

  CalculatorSoapService sut;

  @BeforeEach
  void setUp() {
    sut = new CalculatorSoapService(calculateUseCase);
  }

  @Test
  void add_delegatesToUseCase_andReturnsResult() {
    Calculation calc = mock(Calculation.class);
    when(calc.getResult()).thenReturn(5.0);
    when(calculateUseCase.add(2.0, 3.0)).thenReturn(calc);

    CalculationRequest req = new CalculationRequest(2.0, 3.0);
    CalculationResponse resp = sut.add(req);

    assertEquals(5.0, resp.getResult(), EPS);
    verify(calculateUseCase).add(2.0, 3.0);
  }

  @Test
  void subtract_delegatesToUseCase_andReturnsResult() {
    Calculation calc = mock(Calculation.class);
    when(calc.getResult()).thenReturn(2.0);
    when(calculateUseCase.subtract(5.0, 3.0)).thenReturn(calc);

    CalculationRequest req = new CalculationRequest(5.0, 3.0);
    CalculationResponse resp = sut.subtract(req);

    assertEquals(2.0, resp.getResult(), EPS);
    verify(calculateUseCase).subtract(5.0, 3.0);
  }

  @Test
  void multiply_delegatesToUseCase_andReturnsResult() {
    Calculation calc = mock(Calculation.class);
    when(calc.getResult()).thenReturn(10.0);
    when(calculateUseCase.multiply(2.5, 4.0)).thenReturn(calc);

    CalculationRequest req = new CalculationRequest(2.5, 4.0);
    CalculationResponse resp = sut.multiply(req);

    assertEquals(10.0, resp.getResult(), EPS);
    verify(calculateUseCase).multiply(2.5, 4.0);
  }

  @Test
  void divide_delegatesToUseCase_andReturnsResult() {
    Calculation calc = mock(Calculation.class);
    when(calc.getResult()).thenReturn(3.0);
    when(calculateUseCase.divide(9.0, 3.0)).thenReturn(calc);

    CalculationRequest req = new CalculationRequest(9.0, 3.0);
    CalculationResponse resp = sut.divide(req);

    assertEquals(3.0, resp.getResult(), EPS);
    verify(calculateUseCase).divide(9.0, 3.0);
  }
}