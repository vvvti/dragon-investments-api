package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
class BasketProfitCalculator {

    static final double CASH_RETURN_RATE = 0.02;
    static final double BONDS_RETURN_RATE = 0.05;
    static final double STOCKS_RETURN_RATE = 0.07;
    static final int ASSUMED_INVESTMENT_PERIOD = 30;

    BasketProfitCalculation calculate(Basket basket) {
        BigDecimal finalCash = finalValue(basket.cashValue(), CASH_RETURN_RATE);
        BigDecimal finalBonds = finalValue(basket.bondsValue(), BONDS_RETURN_RATE);
        BigDecimal finalStock = finalValue(basket.stocksValue(), STOCKS_RETURN_RATE);
        BigDecimal finalBasketValue = finalCash.add(finalBonds).add(finalStock);

        return BasketProfitCalculation.builder()
                .finalCash(round(finalCash))
                .finalBonds(round(finalBonds))
                .finalStock(round(finalStock))
                .finalBasketValue(round(finalBasketValue))
                .build();
    }

    private BigDecimal finalValue(BigDecimal initialValue, Double returnRate) {
        return initialValue.multiply(
                BigDecimal.valueOf(1 + returnRate).pow(ASSUMED_INVESTMENT_PERIOD));
    }

    private BigDecimal round(BigDecimal number) {
        return number.setScale(2, RoundingMode.HALF_UP);
    }
}
