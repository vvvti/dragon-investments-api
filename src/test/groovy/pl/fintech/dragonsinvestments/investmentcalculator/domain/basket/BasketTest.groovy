package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket

import spock.lang.Specification

class BasketTest extends Specification {
    def "should basket value not be negative number"() {
        given:
        def basket = new Basket(BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)

        when:
        basket.setValue(-1 as BigDecimal)

        then:
        thrown(IllegalArgumentException)
    }

    def "should change basket value"() {
        given:
        def basket = new Basket(BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)

        when:
        basket.setValue(BigDecimal.ONE as BigDecimal)

        then:
        basket.value == BigDecimal.ONE
    }

    def "should return proper cash value of basket"() {
        given:
        def basket = new Basket(BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)

        when:
        def cash = basket.cashValue()

        then:
        cash == basket.riskType.cashPart() * basket.value
    }

    def "should return proper bonds value of basket"() {
        given:
        def basket = new Basket(BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)

        when:
        def bonds = basket.bondsValue()

        then:
        bonds == basket.riskType.bondsPart() * basket.value
    }

    def "should return proper stock value of basket"() {
        given:
        def basket = new Basket(BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)

        when:
        def stock = basket.stocksValue()

        then:
        stock == basket.riskType.stocksPart() * basket.value
    }
}
