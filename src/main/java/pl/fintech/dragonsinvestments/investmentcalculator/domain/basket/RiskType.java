package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import java.math.BigDecimal;

enum RiskType {
    VERY_CONSERVATIVE(0.2, 0.5, 0.3),
    CONSERVATIVE(0.45, 0.4, 0.15),
    MODERATE(0.65, 0.30, 0.05),
    AGGRESSIVE(0.80, 0.15, 0.05),
    VERY_AGGRESSIVE(0.9, 0.05, 0.05);

    private final BigDecimal cashPart;
    private final BigDecimal bondsPart;
    private final BigDecimal stocksPart;

    RiskType(Double stocksPart, Double bondsPart, Double cashPart) {
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
}
