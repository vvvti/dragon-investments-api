package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "basket")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
class Basket {

    @Id
    private UUID id;

    @NotNull
    @Column(name = "value")
    private BigDecimal value;

    @NotNull
    @Column(name = "risk_type")
    @Enumerated(EnumType.STRING)
    private RiskType riskType;

    Basket(BigDecimal value, RiskType riskType) {
        this.id = UUID.randomUUID();
        setValue(value);
        setRiskType(riskType);
    }

    void setValue(BigDecimal value) {
        if (value == null || isNegative(value)) {
            throw new IllegalArgumentException("Basket value must be correct - value: " + value);
        }
        this.value = value;
    }

    private boolean isNegative(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    void setRiskType(@NonNull RiskType riskType) {
        this.riskType = riskType;
    }

    BigDecimal cashValue() {
        return value.multiply(riskType.cashPart());
    }

    BigDecimal bondsValue() {
        return value.multiply(riskType.bondsPart());
    }

    BigDecimal stocksValue() {
        return value.multiply(riskType.stocksPart());
    }
}
