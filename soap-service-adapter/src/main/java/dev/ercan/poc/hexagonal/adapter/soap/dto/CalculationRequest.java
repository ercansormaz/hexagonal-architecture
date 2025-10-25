package dev.ercan.poc.hexagonal.adapter.soap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculationRequest {

  private double operand1;
  private double operand2;

}