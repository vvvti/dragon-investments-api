package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import spock.lang.Specification;

class InvestmentCalculatorTest extends Specification {
  InvestmentCalculator investmentCalculator = new InvestmentCalculator();

  def "should return result of calculation" (){
    expect:
    investmentCalculator.calculate(a) == b

    where:
    a | b
    new CalculatorParameters(1000 as BigDecimal, 100 as BigDecimal, 2, 5, 12)  | new Calculation(3623.57 as BigDecimal, 223.57 as BigDecimal, 3400.00 as BigDecimal)
    new CalculatorParameters(25000 as BigDecimal, 500 as BigDecimal, 10, 3, 1) | new Calculation(39329.83 as BigDecimal, 9329.83, 30000.00 as BigDecimal )
  }
}
