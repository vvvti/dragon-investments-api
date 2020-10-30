package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@RequiredArgsConstructor
class InvestmentCalculator {

  Calculation calculate(CalculatorParameters calculatorParameters) {
    BigDecimal futureValue = calculatorParameters.getAnnualProfit() == 0 ? interestRateEqualZero(calculatorParameters) : interestRateNotEqualZero(calculatorParameters);
    BigDecimal deposit = depositValue(calculatorParameters);
    BigDecimal profit = futureValue.subtract(deposit);

    return Calculation.of(futureValue, profit, deposit);
  }

  private BigDecimal interestRateEqualZero(CalculatorParameters calculatorParameters) {
    return calculatorParameters.getInitialValue()
        .add(calculatorParameters.getMonthlySaving()
            .multiply(BigDecimal.valueOf(calculatorParameters.getSavingPeriod()))
            .multiply(BigDecimal.valueOf(calculatorParameters.getPaymentFrequency())));
  }

  private BigDecimal interestRateNotEqualZero(CalculatorParameters calculatorParameters) {
    return calculatorParameters.getInitialValue()
        .multiply(BigDecimal.valueOf(Math.pow(1 + calculatorParameters.getAnnualProfit() / 100 / calculatorParameters.getPaymentFrequency(), calculatorParameters.getPaymentFrequency() * calculatorParameters.getSavingPeriod())))
        .add(calculatorParameters.getMonthlySaving()
            .multiply(BigDecimal.valueOf(Math.pow(1 + calculatorParameters.getAnnualProfit() / 100 / calculatorParameters.getPaymentFrequency(), calculatorParameters.getPaymentFrequency() * calculatorParameters.getSavingPeriod()) - 1))
            .divide(BigDecimal.valueOf(calculatorParameters.getAnnualProfit() / 100 / calculatorParameters.getPaymentFrequency()), 2, RoundingMode.HALF_UP));
  }

  private BigDecimal depositValue(CalculatorParameters calculatorParameters) {
    return calculatorParameters.getInitialValue()
        .add(calculatorParameters.getMonthlySaving()
            .multiply(BigDecimal.valueOf((calculatorParameters.getPaymentFrequency() * calculatorParameters.getSavingPeriod()))));
  }
}
