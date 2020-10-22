package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@RequiredArgsConstructor
public class BasketCalculator {

  public static BasketResult calculation(Basket basket) {
    BigDecimal finalCash = finalValue(basket.getValue().multiply(basket.getRiskType().cashPart()), 2.0);
    BigDecimal finalBonds = finalValue(basket.getValue().multiply(basket.getRiskType().bondsPart()), 5.0);
    BigDecimal finalStocks = finalValue(basket.getValue().multiply(basket.getRiskType().stocksPart()), 7.0);

    return BasketResult.builder()
        .id(basket.getId())
        .baskateValue(basket.getValue().setScale(2, RoundingMode.HALF_UP))
        .riskType(basket.getRiskType())
        .initialCashValue(basket.getValue().multiply(basket.getRiskType().cashPart()).setScale(2, RoundingMode.HALF_UP))
        .initialBondsValue(basket.getValue().multiply(basket.getRiskType().bondsPart()).setScale(2, RoundingMode.HALF_UP))
        .initialStocksValue(basket.getValue().multiply(basket.getRiskType().stocksPart()).setScale(2, RoundingMode.HALF_UP))
        .finalCashValue(finalCash.setScale(2, RoundingMode.HALF_UP))
        .finalBondsValue(finalBonds.setScale(2, RoundingMode.HALF_UP))
        .finalStocksValue(finalStocks.setScale(2, RoundingMode.HALF_UP))
        .profitValue(finalCash.add(finalBonds).add(finalStocks).setScale(2, RoundingMode.HALF_UP))
        .build();
  }

  private static BigDecimal finalValue(BigDecimal initialValue, Double profitPercent){
    for(int i = 0; i < 30; i++){
      initialValue = initialValue.add(initialValue.multiply(BigDecimal.valueOf(profitPercent/100)));
    }
    return initialValue;
  }
}
