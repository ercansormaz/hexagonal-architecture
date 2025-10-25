package dev.ercan.poc.hexagonal.infrastructure.adapter;

import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;
import dev.ercan.poc.hexagonal.calculator.domain.model.CalculationType;
import dev.ercan.poc.hexagonal.infrastructure.model.CalculationEntity;
import dev.ercan.poc.hexagonal.infrastructure.repository.CalculationRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationLoggerJpaAdapterTest {

  @Mock
  CalculationRepository calculationRepository;

  CalculationLoggerJpaAdapter sut;

  @BeforeEach
  void setUp() {
    sut = new CalculationLoggerJpaAdapter(calculationRepository);
  }

  @Test
  void log_savesEntityWithCalculationFields() {
    Calculation calculation = mock(Calculation.class);
    when(calculation.getOperand1()).thenReturn(2.5);
    when(calculation.getOperand2()).thenReturn(1.5);
    when(calculation.getType()).thenReturn(CalculationType.ADD);
    when(calculation.getResult()).thenReturn(4.0);
    LocalDateTime now = LocalDateTime.now();
    when(calculation.getTimestamp()).thenReturn(now);

    ArgumentCaptor<CalculationEntity> captor = ArgumentCaptor.forClass(CalculationEntity.class);

    sut.log(calculation);

    verify(calculationRepository, times(1)).save(captor.capture());
    CalculationEntity saved = captor.getValue();

    final double EPS = 1e-9;
    assertEquals(2.5, saved.getOperand1(), EPS);
    assertEquals(1.5, saved.getOperand2(), EPS);
    assertEquals(CalculationType.ADD, saved.getType());
    assertEquals(4.0, saved.getResult(), EPS);
    assertEquals(now, saved.getTimestamp());
  }
}