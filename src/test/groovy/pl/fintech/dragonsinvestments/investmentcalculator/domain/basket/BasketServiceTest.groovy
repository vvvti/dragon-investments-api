package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket

import spock.lang.Specification

import static pl.fintech.dragonsinvestments.investmentcalculator.domain.basket.BasketFixture.BASKET_RESULT
import static pl.fintech.dragonsinvestments.investmentcalculator.domain.basket.BasketFixture.BASKET
import static pl.fintech.dragonsinvestments.investmentcalculator.domain.basket.BasketFixture.BASKET_ID

class BasketServiceTest extends Specification {
    BasketRepository basketRepository = Mock(BasketRepository)
    BasketProfitCalculator basketProfitCalculator = Mock(BasketProfitCalculator)
    BasketResultAssembler basketResultAssembler = Mock(BasketResultAssembler)
    BasketService basketService = new BasketService(basketRepository, basketProfitCalculator, basketResultAssembler)

    def "should create new basket"() {
        given:
        def basketDto = new BasketDto(BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)
        and:
        databaseWorks()
        and:
        calculatorWorks()
        and:
        assemblerWorks()

        when:
        def result = basketService.create(basketDto)

        then:
        resultMatches(result, BASKET_RESULT)
    }

    def "should return basket with id"() {
        given:
        thereIsBasketWithId()
        and:
        calculatorWorks()
        and:
        assemblerWorks()

        when:
        def result = basketService.getBasket(BASKET_ID)

        then:
        resultMatches(result, BASKET_RESULT)
    }

    def "should update basket with id"() {
        given:
        def basketDto = new BasketDto(BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)
        and:
        databaseWorks()
        and:
        thereIsBasketWithId()
        and:
        calculatorWorks()
        and:
        assemblerWorks()

        when:
        def result = basketService.update(BASKET_ID, basketDto)

        then:
        resultMatches(result, BASKET_RESULT)
    }

    void databaseWorks() {
        basketRepository.save(_ as Basket) >> null
    }

    void calculatorWorks() {
        basketProfitCalculator.calculate(_ as Basket) >> null
    }

    void assemblerWorks() {
        _ * basketResultAssembler.toResult(_ , _) >> BASKET_RESULT
    }

    void thereIsBasketWithId() {
        basketRepository.getOne(BASKET_ID) >> BASKET
    }

    void resultMatches(BasketResult result, BasketResult matcher) {
        result.id == matcher.id
        result.profit == matcher.profit
        result.totalAmount == matcher.totalAmount
        result.currency == matcher.currency
        result.riskType == matcher.riskType
        result.basketValue == matcher.basketValue
    }
}
