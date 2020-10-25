package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket

import spock.lang.Specification

class BasketResultAssemblerTest extends Specification {

    private BasketResultAssembler basketResultAssembler = new BasketResultAssembler();

    def "Should create proper basket dto result"() {
        given:
        def basket = new Basket(BigDecimal.valueOf(1), Currency.EUR, RiskType.Aggressive)
        def calculation = BasketProfitCalculation.builder()
                .finalStock(BigDecimal.valueOf(2))
                .finalBonds(BigDecimal.valueOf(3))
                .finalCash(BigDecimal.valueOf(4))
                .finalBasketValue(BigDecimal.valueOf(5))
                .build()

        when:
        def result = basketResultAssembler.toResult(basket, calculation)

        then:
        result.id == basket.id
        result.basketValue == basket.value
        result.currency == basket.currency
        result.riskType == basket.riskType
        result.totalAmount == calculation.finalBasketValue
        result.profit.bonds == calculation.finalBonds - basket.bondsValue()
        result.profit.stock == calculation.finalStock - basket.stocksValue()
        result.profit.cash == calculation.finalCash - basket.cashValue()
    }
}
