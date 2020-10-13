package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@EqualsAndHashCode
public class CalculatorResult {
  private BigDecimal finalValue;
  private BigDecimal estimatedProfit;
  private BigDecimal depositValue;
}
