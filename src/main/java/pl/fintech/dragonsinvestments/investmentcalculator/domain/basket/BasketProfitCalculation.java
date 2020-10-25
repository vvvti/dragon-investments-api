package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
class BasketProfitCalculation {
    @NonNull BigDecimal finalCash;
    @NonNull BigDecimal finalBonds;
    @NonNull BigDecimal finalStock;
    @NonNull BigDecimal finalBasketValue;
}
