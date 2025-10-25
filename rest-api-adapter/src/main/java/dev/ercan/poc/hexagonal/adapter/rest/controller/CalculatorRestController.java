package dev.ercan.poc.hexagonal.adapter.rest.controller;

import dev.ercan.poc.hexagonal.adapter.rest.dto.CalculationRequest;
import dev.ercan.poc.hexagonal.adapter.rest.dto.CalculationResponse;
import dev.ercan.poc.hexagonal.calculator.domain.port.in.CalculateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/calculator")
public class CalculatorRestController {

  private final CalculateUseCase calculateUseCase;

  @PostMapping("/add")
  public CalculationResponse add(@RequestBody CalculationRequest request) {
    return new CalculationResponse(
        calculateUseCase.add(request.operand1(), request.operand2()).getResult());
  }

  @PostMapping("/subtract")
  public CalculationResponse subtract(@RequestBody CalculationRequest request) {
    return new CalculationResponse(
        calculateUseCase.subtract(request.operand1(), request.operand2()).getResult());
  }

  @PostMapping("/multiply")
  public CalculationResponse multiply(@RequestBody CalculationRequest request) {
    return new CalculationResponse(
        calculateUseCase.multiply(request.operand1(),
            request.operand2()).getResult());
  }


  @PostMapping("/divide")
  public CalculationResponse divide(@RequestBody CalculationRequest request) {
    return new CalculationResponse(
        calculateUseCase.divide(request.operand1(), request.operand2()).getResult());
  }

}