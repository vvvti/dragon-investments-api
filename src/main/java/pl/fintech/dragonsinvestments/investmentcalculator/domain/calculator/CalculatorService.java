package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
class CalculatorService {

    private final InvestmentCalculator investmentCalculator;

    CalculationResultDto getCalculateResult(CalculatorParameters calculatorParameters) {
        List<PeriodCalculationDto> periodCalculations = new ArrayList<>();
        Calculation result = null;
        for (int period = 0; period <= calculatorParameters.getSavingPeriod(); period++) {
            result = investmentCalculator.calculate(new CalculatorParameters(
                calculatorParameters.getInitialValue(),
                calculatorParameters.getMonthlySaving(),
                period,
                calculatorParameters.getAnnualProfit(),
                calculatorParameters.getPaymentFrequency()));
            periodCalculations.add(new PeriodCalculationDto(
                period,
                result.getFinalValue(),
                result.getEstimatedProfit(),
                result.getDepositValue()));
        }

        return new CalculationResultDto(
                result.getFinalValue(),
                result.getEstimatedProfit(),
                result.getDepositValue(),
                periodCalculations);
    }
}
