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
    private Currency currency;
    private BigDecimal basketValue;
    private BigDecimal totalAmount;
    private Profit profit;
}

@AllArgsConstructor
@Getter
class Profit {
    private BigDecimal cash;
    private BigDecimal bonds;
    private BigDecimal stock;
}
