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
}
