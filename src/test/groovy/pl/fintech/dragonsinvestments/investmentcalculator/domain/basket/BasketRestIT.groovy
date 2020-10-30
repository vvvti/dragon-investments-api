package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll
import spock.mock.DetachedMockFactory

import static groovy.json.JsonOutput.toJson
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static pl.fintech.dragonsinvestments.investmentcalculator.domain.basket.BasketFixture.*

@Title("BasketController test")
@WebMvcTest(controllers = BasketController.class)
class BasketRestIT extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    BasketService basketService

    def 'GET /basket/id should return HTTP 200 with basket result'() {
        given:
        thereIsBasketResultFor(BASKET_ID, BASKET_RESULT)

        when:
        def result = mockMvc.perform(get('/basket/' + BASKET_ID))

        then:
        result.andExpect(status().isOk())

        and:
        basketResultIsProper(result, BASKET_RESULT)
    }

    def 'POST /basket should return HTTP 200 with basket result after basket creation'() {
        given:
        basketWillBeCreated(BASKET_DTO, BASKET_RESULT)

        when:
        def result = mockMvc.perform(post('/basket').contentType(APPLICATION_JSON).content(toJson(BASKET_DTO)))

        then:
        result.andExpect(status().isCreated())

        and:
        basketResultIsProper(result, BASKET_RESULT)
    }

    @Unroll
    def 'POST /basket should return HTTP 400 when invalid basket value: #basketValue'() {
        given:
        def basketDto = new BasketDto(basketValue, Currency.EUR, RiskType.CONSERVATIVE)

        when:
        def result = mockMvc.perform(post('/basket').contentType(APPLICATION_JSON).content(toJson(basketDto)))

        then:
        result.andExpect(status().isBadRequest())

        where:
        basketValue               || userMessage
        BigDecimal.ZERO           || 'Invalid basket value.'
        -1 as BigDecimal          || 'Invalid basket value.'
        null                      || 'Basket value must be provided.'
    }

    def 'PUT /basket/id should return HTTP 200 with basket result after basket update'() {
        given:
        basketWillBeUpdated(BASKET_ID, BASKET_DTO, BASKET_RESULT)

        when:
        def result = mockMvc.perform(put('/basket/' + BASKET_ID).contentType(APPLICATION_JSON).content(toJson(BASKET_DTO)))

        then:
        result.andExpect(status().isOk())

        and:
        basketResultIsProper(result, BASKET_RESULT)
    }

    @Unroll
    def 'PUT /basket/id should return HTTP 400 when invalid basketValue: #basketValue'() {
        given:
        def basketDto = new BasketDto(basketValue, Currency.EUR, RiskType.CONSERVATIVE)

        when:
        def result = mockMvc.perform(put('/basket/' + BASKET_ID).contentType(APPLICATION_JSON).content(toJson(basketDto)))

        then:
        result.andExpect(status().isBadRequest())

        where:
        basketValue               || userMessage
        BigDecimal.ZERO           || 'Invalid basket value.'
        -1 as BigDecimal          || 'Invalid basket value.'
        null                      || 'Basket value must be provided.'
    }

    void thereIsBasketResultFor(UUID id, BasketResult result) {
        basketService.getBasket(id) >> result
    }

    void basketWillBeCreated(BasketDto basketDto, BasketResult result) {
        basketService.create(basketDto) >> result
    }

    void basketWillBeUpdated(UUID id, BasketDto basketDto, BasketResult result) {
        basketService.update(id, basketDto) >> result
    }

    void basketResultIsProper(ResultActions result, BasketResult basketResult) {
        result
                .andExpect(jsonPath('$.id').value(basketResult.getId().toString()))
                .andExpect(jsonPath('$.riskType').value(basketResult.getRiskType().name()))
                .andExpect(jsonPath('$.currency').value(basketResult.getCurrency().name()))
                .andExpect(jsonPath('$.basketValue').value(basketResult.getBasketValue()))
                .andExpect(jsonPath('$.totalAmount').value(basketResult.getTotalAmount()))
                .andExpect(jsonPath('$.profit.cash').value(basketResult.getProfit().getCash()))
                .andExpect(jsonPath('$.profit.bonds').value(basketResult.getProfit().getBonds()))
                .andExpect(jsonPath('$.profit.stock').value(basketResult.getProfit().getStock()))
    }

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        BasketService basketService() {
            return detachedMockFactory.Stub(BasketService)
        }
    }
}