package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
class BasketResult {
    private UUID id;
    private RiskType riskType;
    private BigDecimal basketValue;
    private BigDecimal totalAmount;
    private Profit profit;
    private PercentageTypeInwestment percentage;
}

@AllArgsConstructor
@Getter
class Profit {
    private BigDecimal cash;
    private BigDecimal bonds;
    private BigDecimal stock;
}

@AllArgsConstructor
@Getter
class PercentageTypeInwestment {
    private double cash;
    private double bonds;
    private double stock;
}
