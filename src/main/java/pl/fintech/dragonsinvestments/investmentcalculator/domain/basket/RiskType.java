package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

enum RiskType {
    VeryConservative(0.2, 0.5, 0.3),
    Conservative(0.45, 0.4, 0.15),
    Moderate(0.65, 0.30, 0.05),
    Aggressive(0.80, 0.15, 0.05),
    VeryAggressive(0.9, 0.05, 0.05);

    private final BigDecimal cashPart;
    private final BigDecimal bondsPart;
    private final BigDecimal stocksPart;

    RiskType(Double cashPart, Double bondsPart, Double stocksPart) {
        this.cashPart = BigDecimal.valueOf(cashPart);
        this.bondsPart = BigDecimal.valueOf(bondsPart);
        this.stocksPart = BigDecimal.valueOf(stocksPart);
    }

    BigDecimal cashPart() {
        return this.cashPart;
    }

    BigDecimal bondsPart() {
        return this.bondsPart;
    }

    BigDecimal stocksPart() {
        return this.stocksPart;
    }

    @JsonValue
    public String getType(){
        return this.name().toUpperCase();
    }
}
