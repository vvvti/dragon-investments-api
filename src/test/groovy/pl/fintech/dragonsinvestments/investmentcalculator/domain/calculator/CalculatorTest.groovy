package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import spock.lang.Specification;

class CalculatorTest extends Specification {
  CalculatorService calculatorService = new CalculatorService();

  def "should return result of calculation" (){
    expect:
    calculatorService.getCalculateResult(a) == b

    where:
    a | b
    new CalculatorData(1000 as BigDecimal, 100 as BigDecimal, 2, 5, 12) | new CalculatorResult(3623.57 as BigDecimal, 223.57 as BigDecimal, 3400.00 as BigDecimal)
    new CalculatorData(25000 as BigDecimal, 500 as BigDecimal, 10, 3, 1) | new CalculatorResult(39329.83 as BigDecimal, 9329.83, 30000.00 as BigDecimal )
  }
}
