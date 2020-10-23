package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
class BasketDto {

    @DecimalMin(value = "0", inclusive = false, message = "Basket value must be greater than zero")
    @NotNull
    BigDecimal value;

    @NotNull
    RiskType riskType;
}
