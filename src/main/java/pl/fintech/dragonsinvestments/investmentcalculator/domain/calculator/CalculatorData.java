package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorData {

  @NotNull
  @Min(value = 0, message = "Initial value should not be less than 0")
  private BigDecimal initialValue;

  @NotNull
  @Min(value = 0, message = "Monthly saving value should not be less than 0")
  private BigDecimal monthlySaving;

  @NotNull
  @Min(value = 0, message = "Saving period value should not be less than 0")
  private Integer savingPeriod;

  @NotNull
  @Min(value = 0, message = "Annual profit value should not be less than 0")
  private Float annualProfit;

  @NotNull
  @Min(value = 0, message = "Annual profit value should not be less than 0")
  //@Max(value = 12, message = "Annual profit value should not be greater than 12")
  private Integer paymentFrequency;
}
