package dev.ercan.poc.hexagonal.infrastructure.adapter;

import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;
import dev.ercan.poc.hexagonal.calculator.domain.port.out.CalculationLogger;
import dev.ercan.poc.hexagonal.infrastructure.model.CalculationEntity;
import dev.ercan.poc.hexagonal.infrastructure.repository.CalculationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalculationLoggerJpaAdapter implements CalculationLogger {

  private final CalculationRepository calculationRepository;

  @Override
  public void log(Calculation calculation) {
    CalculationEntity entity = new CalculationEntity();
    entity.setOperand1(calculation.getOperand1());
    entity.setOperand2(calculation.getOperand2());
    entity.setType(calculation.getType());
    entity.setResult(calculation.getResult());
    entity.setTimestamp(calculation.getTimestamp());
    calculationRepository.save(entity);
  }

}
