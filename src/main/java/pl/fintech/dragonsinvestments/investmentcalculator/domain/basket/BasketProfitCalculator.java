package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
class BasketProfitCalculator {

    protected static final double CASH_RETURN_RATE = 0.02;
    protected static final double BONDS_RETURN_RATE = 0.05;
    protected static final double STOCKS_RETURN_RATE = 0.07;
    protected static final int ASSUMED_INVESTMENT_PERIOD = 30;

    BasketProfitCalculation calculate(Basket basket) {
        BigDecimal finalCash = finalValue(basket.cashValue(), CASH_RETURN_RATE);
        BigDecimal finalBonds = finalValue(basket.bondsValue(), BONDS_RETURN_RATE);
        BigDecimal finalStock = finalValue(basket.stocksValue(), STOCKS_RETURN_RATE);
        BigDecimal finalBasketValue = finalCash.add(finalBonds).add(finalStock);

        return BasketProfitCalculation.builder()
                .finalCash(finalCash)
                .finalBonds(finalBonds)
                .finalStock(finalStock)
                .finalBasketValue(finalBasketValue)
                .build();
    }

    private static BigDecimal finalValue(BigDecimal initialValue, Double returnRate) {
        for (int i = 1; i <= ASSUMED_INVESTMENT_PERIOD; i++) {
            initialValue = initialValue.add(initialValue.multiply(BigDecimal.valueOf(returnRate)));
        }
        return initialValue;
    }
}
