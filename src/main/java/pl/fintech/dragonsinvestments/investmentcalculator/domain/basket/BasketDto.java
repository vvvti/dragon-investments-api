package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
class BasketDto {

    @DecimalMin(value = "0", inclusive = false, message = "Basket value must be greater than zero")
    @NotNull
    @JsonProperty("basketValue")
    BigDecimal value;

    @NotNull
    Currency currency;

    @NotNull
    RiskType riskType;
}
