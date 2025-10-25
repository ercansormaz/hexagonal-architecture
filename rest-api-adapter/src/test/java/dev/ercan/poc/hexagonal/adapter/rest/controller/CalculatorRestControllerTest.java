package dev.ercan.poc.hexagonal.adapter.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ercan.poc.hexagonal.calculator.domain.model.Calculation;
import dev.ercan.poc.hexagonal.calculator.domain.port.in.CalculateUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorRestControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockitoBean
  CalculateUseCase calculateUseCase;

  @Test
  void addEndpoint_returnsResultAndDelegatesToUseCase() throws Exception {
    Calculation calc = mock(Calculation.class);
    when(calculateUseCase.add(2.0, 3.0)).thenReturn(calc);
    when(calc.getResult()).thenReturn(5.0);

    var request = Map.of("operand1", 2.0, "operand2", 3.0);

    mockMvc.perform(post("/rest/calculator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.result").value(5.0));

    verify(calculateUseCase).add(2.0, 3.0);
  }

  @Test
  void subtractEndpoint_returnsResultAndDelegatesToUseCase() throws Exception {
    Calculation calc = mock(Calculation.class);
    when(calculateUseCase.subtract(10.0, 4.5)).thenReturn(calc);
    when(calc.getResult()).thenReturn(5.5);

    var request = Map.of("operand1", 10.0, "operand2", 4.5);

    mockMvc.perform(post("/rest/calculator/subtract")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value(5.5));

    verify(calculateUseCase).subtract(10.0, 4.5);
  }

  @Test
  void multiplyEndpoint_returnsResultAndDelegatesToUseCase() throws Exception {
    Calculation calc = mock(Calculation.class);
    when(calculateUseCase.multiply(2.5, 4.0)).thenReturn(calc);
    when(calc.getResult()).thenReturn(10.0);

    var request = Map.of("operand1", 2.5, "operand2", 4.0);

    mockMvc.perform(post("/rest/calculator/multiply")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value(10.0));

    verify(calculateUseCase).multiply(2.5, 4.0);
  }

  @Test
  void divideEndpoint_returnsResultAndDelegatesToUseCase() throws Exception {
    Calculation calc = mock(Calculation.class);
    when(calculateUseCase.divide(9.0, 3.0)).thenReturn(calc);
    when(calc.getResult()).thenReturn(3.0);

    var request = Map.of("operand1", 9.0, "operand2", 3.0);

    mockMvc.perform(post("/rest/calculator/divide")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.result").value(3.0));

    verify(calculateUseCase).divide(9.0, 3.0);
  }
}