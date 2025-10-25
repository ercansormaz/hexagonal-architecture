package dev.ercan.poc.hexagonal.adapter.soap.service;

import dev.ercan.poc.hexagonal.adapter.soap.dto.CalculationRequest;
import dev.ercan.poc.hexagonal.adapter.soap.dto.CalculationResponse;
import dev.ercan.poc.hexagonal.calculator.domain.port.in.CalculateUseCase;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@WebService(targetNamespace = "https://ercan.dev/poc/hexagonal-architecture/calculator")
public class CalculatorSoapService {

  private final CalculateUseCase calculateUseCase;

  @WebResult(name = "response")
  public CalculationResponse add(@WebParam(name = "request") CalculationRequest request) {
    return new CalculationResponse(
        calculateUseCase.add(request.getOperand1(), request.getOperand2()).getResult());
  }

  @WebResult(name = "response")
  public CalculationResponse subtract(@WebParam(name = "request") CalculationRequest request) {
    return new CalculationResponse(
        calculateUseCase.subtract(request.getOperand1(), request.getOperand2()).getResult());
  }

  @WebResult(name = "response")
  public CalculationResponse multiply(@WebParam(name = "request") CalculationRequest request) {
    return new CalculationResponse(
        calculateUseCase.multiply(request.getOperand1(), request.getOperand2()).getResult());
  }

  @WebResult(name = "response")
  public CalculationResponse divide(@WebParam(name = "request") CalculationRequest request) {
    return new CalculationResponse(
        calculateUseCase.divide(request.getOperand1(), request.getOperand2()).getResult());
  }

}
