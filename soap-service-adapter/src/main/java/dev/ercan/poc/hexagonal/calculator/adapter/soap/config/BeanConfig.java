package dev.ercan.poc.hexagonal.calculator.adapter.soap.config;

import dev.ercan.poc.hexagonal.calculator.application.usecase.CalculateUseCaseImpl;
import dev.ercan.poc.hexagonal.calculator.domain.port.in.CalculateUseCase;
import dev.ercan.poc.hexagonal.calculator.domain.port.out.CalculationLogger;
import dev.ercan.poc.hexagonal.calculator.domain.service.CalculatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

  @Bean
  CalculatorService calculatorService() {
    return new CalculatorService();
  }

  @Bean
  CalculateUseCase calculateUseCase(CalculatorService calculatorService,
      CalculationLogger calculationLogger) {
    return new CalculateUseCaseImpl(calculatorService, calculationLogger);
  }

}
