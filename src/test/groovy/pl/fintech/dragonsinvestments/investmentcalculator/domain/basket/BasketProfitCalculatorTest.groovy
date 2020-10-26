package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket

import spock.lang.Specification

class BasketProfitCalculatorTest extends Specification {

    BasketProfitCalculator basketProfitCalculator = new BasketProfitCalculator()

    def "should return proper final cash profit value in calculation"() {
        given:
        def basket = new Basket(5000 as BigDecimal, Currency.EUR, RiskType.VeryConservative)

        when:
        def result = basketProfitCalculator.calculate(basket)

        then:
        result.finalCash == 1811.36
    }

    def "should return proper final bonds profit value in calculation"() {
        given:
        def basket = new Basket(2000 as BigDecimal, Currency.EUR, RiskType.VeryConservative)

        when:
        def result = basketProfitCalculator.calculate(basket)

        then:
        result.finalBonds == 4321.94
    }

    def "should return proper final stock profit value in calculation"() {
        given:
        def basket = new Basket(1000 as BigDecimal, Currency.EUR, RiskType.VeryConservative)

        when:
        def result = basketProfitCalculator.calculate(basket)

        then:
        result.finalStock == 2283.68
    }

    def "should return proper final basket value in calculation"() {
        given:
        def basket = new Basket(1000 as BigDecimal, Currency.EUR, RiskType.VeryConservative)

        when:
        def result = basketProfitCalculator.calculate(basket)

        then:
        result.finalBasketValue == 362.27 + 2160.97 + 2283.68
    }
}
