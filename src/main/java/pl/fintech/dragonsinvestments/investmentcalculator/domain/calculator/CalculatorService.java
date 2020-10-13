package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorService {
  public CalculatorResult getCalculateResult(CalculatorData calculatorData) {
    BigDecimal futureValue = calculatorData.getAnnualProfit() == 0 ? interestRateEqualZero(calculatorData):interestRateNotEqualZero(calculatorData);
    BigDecimal deposit = depositValue(calculatorData);
    BigDecimal profit = futureValue.subtract(deposit);

    return CalculatorResult.builder()
        .finalValue(futureValue.setScale(2, RoundingMode.HALF_UP))
        .estimatedProfit(profit.setScale(2, RoundingMode.HALF_UP))
        .depositValue(deposit.setScale(2, RoundingMode.HALF_UP))
        .build();
  }

  /* initialValue + monthlySaving * savingPeriod */
  private BigDecimal  interestRateEqualZero(CalculatorData calculatorData) {
    return calculatorData.getInitialValue()
        .add(calculatorData.getMonthlySaving()
            .multiply(BigDecimal.valueOf(calculatorData.getSavingPeriod())));
  }

  /* (initialValue * (1 + annualProfit / 100 / paymentFrequency)^(paymentFrequency * savingPeriod)) + (monthlySaving * (1 + annualProfit / 100 / paymentFrequency)^(paymentFrequency * savingPeriod)) */
  private BigDecimal interestRateNotEqualZero (CalculatorData calculatorData) {
    return calculatorData.getInitialValue()
        .multiply(BigDecimal.valueOf(Math.pow(1 + calculatorData.getAnnualProfit()/100 / calculatorData.getPaymentFrequency(), calculatorData.getPaymentFrequency() * calculatorData.getSavingPeriod())))
        .add(calculatorData.getMonthlySaving()
            .multiply(BigDecimal.valueOf(Math.pow(1 + calculatorData.getAnnualProfit()/100 / calculatorData.getPaymentFrequency(), calculatorData.getPaymentFrequency() * calculatorData.getSavingPeriod())-1))
            .divide(BigDecimal.valueOf(calculatorData.getAnnualProfit()/100 / calculatorData.getPaymentFrequency()), 2,RoundingMode.HALF_UP));
  }

  /* initialValue + monthlySaving * paymentFrequency */
  private BigDecimal depositValue (CalculatorData calculatorData) {
    return calculatorData.getInitialValue()
        .add(calculatorData.getMonthlySaving()
            .multiply(BigDecimal.valueOf((calculatorData.getPaymentFrequency() * calculatorData.getSavingPeriod()))));
  }
}
