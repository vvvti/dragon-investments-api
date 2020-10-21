package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
public class BasketResult {
  private UUID id;
  private RiskType riskType;
  private BigDecimal baskateValue;
  private BigDecimal initialCashValue;
  private BigDecimal initialBondsValue;
  private BigDecimal initialStocksValue;
  private BigDecimal finalCashValue;
  private BigDecimal finalBondsValue;
  private BigDecimal finalStocksValue;
  private BigDecimal profitValue;
}
