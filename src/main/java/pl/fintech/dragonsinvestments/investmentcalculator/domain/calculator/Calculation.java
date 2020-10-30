package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Calculation {
  BigDecimal finalValue;
  BigDecimal estimatedProfit;
  BigDecimal depositValue;

  static Calculation of(@NonNull BigDecimal finalValue, @NonNull BigDecimal estimatedProfit, @NonNull BigDecimal depositValue) {
    return new Calculation(
            finalValue.setScale(2, RoundingMode.HALF_UP),
            estimatedProfit.setScale(2, RoundingMode.HALF_UP),
            depositValue.setScale(2, RoundingMode.HALF_UP));
  }
}
