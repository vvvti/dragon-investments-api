package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorParameters {

  @NotNull
  @Min(value = 0, message = "Initial value should not be less than 0")
  private BigDecimal initialValue;

  @NotNull
  @Min(value = 0, message = "Monthly saving value should not be less than 0")
  private BigDecimal monthlySaving;

  @NotNull
  @Min(value = 1, message = "Saving period value should not be less than 0")
  @Max(value = 50, message = "Saving period value should not be greater than 50")
  private Integer savingPeriod;

  @NotNull
  @Min(value = 0, message = "Annual profit value should not be less than 0")
  private float annualProfit;

  @NotNull
  @Min(value = 1, message = "Payment frequency value should not be less than 1")
  @Max(value = 12, message = "Payment frequency value should not be greater than 12")
  private int paymentFrequency;
}
