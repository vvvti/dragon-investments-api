package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class BasketDto {
  private UUID id;
  private BigDecimal value;
  RiskType riskType;
}
