package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class CalculatorController {
  private final CalculatorService calculatorService;

  @Value("${pl.fintech.app-name}")
  private String applicationName;
  private static final String ENTITY_NAME = "calculator";

  @GetMapping("/calculator")
  public ResponseEntity<CalculatorResult> getCalculate(@Valid CalculatorData calculatorData) {
    log.debug("REST request to get result of investment calculation");
    return new ResponseEntity<>(calculatorService.getCalculateResult(calculatorData), HttpStatus.OK);
  }
}
