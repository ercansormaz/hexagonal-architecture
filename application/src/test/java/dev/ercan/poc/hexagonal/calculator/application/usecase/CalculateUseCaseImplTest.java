package dev.ercan.poc.hexagonal.calculator.application.usecase;

import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;
import dev.ercan.poc.hexagonal.calculator.domain.model.CalculationType;
import dev.ercan.poc.hexagonal.calculator.domain.port.out.CalculationLogger;
import dev.ercan.poc.hexagonal.calculator.domain.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculateUseCaseImplTest {

  @Mock
  CalculatorService calculatorService;

  @Mock
  CalculationLogger calculationLogger;

  @InjectMocks
  CalculateUseCaseImpl sut;

  @Test
  void add_delegatesToCalculatorService_and_logs_and_returnsCalculation() {
    Calculation calc = mock(Calculation.class);
    when(calculatorService.calculate(2.0, 3.0, CalculationType.ADD)).thenReturn(calc);

    Calculation result = sut.add(2.0, 3.0);

    assertSame(calc, result);
    verify(calculatorService).calculate(2.0, 3.0, CalculationType.ADD);
    verify(calculationLogger).log(calc);

    InOrder inOrder = inOrder(calculatorService, calculationLogger);
    inOrder.verify(calculatorService).calculate(2.0, 3.0, CalculationType.ADD);
    inOrder.verify(calculationLogger).log(calc);
  }

  @Test
  void add_forwardsOperandsAndType() {
    Calculation calc = mock(Calculation.class);
    when(calculatorService.calculate(anyDouble(), anyDouble(), any())).thenReturn(calc);

    sut.add(5.5, -1.25);

    verify(calculatorService).calculate(eq(5.5), eq(-1.25), eq(CalculationType.ADD));
  }
}