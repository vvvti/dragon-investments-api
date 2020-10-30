package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
class CalculatorController {
  private final CalculatorService calculatorService;

  @Value("${pl.fintech.app-name}")
  private String applicationName;
  private static final String ENTITY_NAME = "calculator";

  @Operation(summary = "Get result of calculation")
  @GetMapping("/calculator")
  CalculationResultDto getCalculation(@Valid CalculatorParameters calculatorParameters) {
    log.debug("REST request to get result of investment calculation");
    return calculatorService.getCalculateResult(calculatorParameters);
  }
}
