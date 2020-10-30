package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator

import spock.lang.Specification

class CalculatorServiceTest extends Specification {

    def investmentCalculator = Mock(InvestmentCalculator)
    def calculatorService = new CalculatorService(investmentCalculator)

    def "should investment calculator be called the right number of times" () {
        given:
        def periodsNumber = 10
        def calculatorParameters = new CalculatorParameters(1000 as BigDecimal, 100 as BigDecimal, periodsNumber, 5, 12)
        def calculation = Calculation.of(1000 as BigDecimal, 1000 as BigDecimal, 1000 as BigDecimal)

        when:
        calculatorService.getCalculateResult(calculatorParameters)

        then:
        (periodsNumber + 1) * investmentCalculator.calculate(_ as CalculatorParameters) >> calculation
    }

    def 'should return proper result of calculation' () {
        given:

        def params = new CalculatorParameters(25000 as BigDecimal, 500 as BigDecimal, 3, 3, 1)

        def periodListResult = Arrays.asList(
                new PeriodCalculationDto(0, BigDecimal.valueOf(25000.00), BigDecimal.valueOf(0.00), BigDecimal.valueOf(25000.00)),
                new PeriodCalculationDto(1, BigDecimal.valueOf(26250.00), BigDecimal.valueOf(750), BigDecimal.valueOf(25500.00)),
                new PeriodCalculationDto(2, BigDecimal.valueOf(27537.50), BigDecimal.valueOf(1537.50), BigDecimal.valueOf(26000.00)),
                new PeriodCalculationDto(3, BigDecimal.valueOf(28863.62), BigDecimal.valueOf(2363.62), BigDecimal.valueOf(26500.00)))

        def result = new CalculationResultDto(BigDecimal.valueOf(28863.62), BigDecimal.valueOf(2363.62), BigDecimal.valueOf(26500.00), periodListResult)

        def calculation1 = new Calculation(BigDecimal.valueOf(25000.00), BigDecimal.valueOf(0.00), BigDecimal.valueOf(25000.00))
        def calculation2 = new Calculation(BigDecimal.valueOf(26250.00), BigDecimal.valueOf(750), BigDecimal.valueOf(25500.00))
        def calculation3 = new Calculation(BigDecimal.valueOf(27537.50), BigDecimal.valueOf(1537.50), BigDecimal.valueOf(26000.00))
        def calculation4 = new Calculation(BigDecimal.valueOf(28863.62), BigDecimal.valueOf(2363.62), BigDecimal.valueOf(26500.00))

        investmentCalculator.calculate(_ as CalculatorParameters) >>> [calculation1, calculation2, calculation3, calculation4]

        when:
        def resultCalculation = calculatorService.getCalculateResult(params)

        then:
        resultCalculation == result
    }
}
