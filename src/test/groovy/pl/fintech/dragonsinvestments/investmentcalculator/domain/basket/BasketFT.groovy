package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import pl.fintech.dragonsinvestments.investmentcalculator.PostgreSQLContainerSpecification

import static groovy.json.JsonOutput.toJson
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class BasketFT extends PostgreSQLContainerSpecification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    BasketService basketService

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    BasketRepository repository

    def 'should create new basket \
        and then have possibility to retrieve it'() {
        given:
        def basketDto = new BasketDto(1000 as BigDecimal, Currency.EUR, RiskType.VERY_CONSERVATIVE)

        when:
        UUID id = thereIsCreateRequestWith(basketDto)

        and:
        def result = mockMvc.perform(get('/basket/' + id))

        then:
        result
                .andExpect(jsonPath('$.id').value(id.toString()))
                .andExpect(jsonPath('$.riskType').value(basketDto.getRiskType().name()))
                .andExpect(jsonPath('$.currency').value(basketDto.getCurrency().name()))
                .andExpect(jsonPath('$.basketValue').value(1000))
                .andExpect(jsonPath('$.totalAmount').value(543.41 + 2160.97 + 1522.45))
                .andExpect(jsonPath('$.profit.cash').value(543.41 - 1000 * 0.3))
                .andExpect(jsonPath('$.profit.bonds').value(2160.97 - 1000 * 0.5))
                .andExpect(jsonPath('$.profit.stock').value(1522.45 - 1000 * 0.2))
    }

    def 'should create new basket \
        and then have possibility to modify it'() {
        given:
        def createDto = new BasketDto(BigDecimal.TEN, Currency.USD, RiskType.CONSERVATIVE)
        def modifyDto = new BasketDto(1000 as BigDecimal, Currency.EUR, RiskType.VERY_CONSERVATIVE)

        when:
        UUID id = thereIsCreateRequestWith(createDto)

        and:
        def result = mockMvc.perform(
                put('/basket/' + id).contentType(APPLICATION_JSON).content(toJson(modifyDto))
        )

        then:
        result
                .andExpect(jsonPath('$.id').value(id.toString()))
                .andExpect(jsonPath('$.riskType').value(modifyDto.getRiskType().name()))
                .andExpect(jsonPath('$.currency').value(modifyDto.getCurrency().name()))
                .andExpect(jsonPath('$.basketValue').value(1000))
                .andExpect(jsonPath('$.totalAmount').value(543.41 + 2160.97 + 1522.45))
                .andExpect(jsonPath('$.profit.cash').value(543.41 - 1000 * 0.3))
                .andExpect(jsonPath('$.profit.bonds').value(2160.97 - 1000 * 0.5))
                .andExpect(jsonPath('$.profit.stock').value(1522.45 - 1000 * 0.2))
    }

    UUID thereIsCreateRequestWith(BasketDto basketDto) {
        def basketResultFromCreation = mockMvc.perform(
                post('/basket').contentType(APPLICATION_JSON).content(toJson(basketDto))
        ).andReturn().response.contentAsString
        return objectMapper.readValue(basketResultFromCreation, BasketResult).id
    }

    def cleanup() {
        repository.deleteAll()
    }
}