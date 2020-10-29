package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Title
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Title("Calculator controller test")
@WebMvcTest(controllers = CalculatorController.class)
class CalculatorIT extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    CalculatorService calculatorServiceMock

    def 'GET /calculator should return HTTP 200 with calculator result dto'() {

        given: 'Mock calculatorService get'
        List<PeriodCalculationDto> period = new ArrayList<>()
        period.add(new PeriodCalculationDto(
                0,
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(1000)))

        def calculation = new CalculationResultDto(
                BigDecimal.valueOf(3000),
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(1000),
                period)

        calculatorServiceMock.getCalculateResult(_ as CalculatorParameters) >> calculation

        expect: 'GET /calculator returned json with calculator result dto HTTP OK status'
        mockMvc.perform(get('/calculator')
                .param("initialValue", "1000")
                .param("monthlySaving", "100")
                .param("savingPeriod", "1")
                .param("annualProfit", "1")
                .param("paymentFrequency", "12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.finalValue').value(calculation.getFinalValue()))
                .andExpect(jsonPath('$.estimatedProfit').value(calculation.getEstimatedProfit()))
                .andExpect(jsonPath('$.depositValue').value(calculation.getDepositValue()))
                .andExpect(jsonPath('$.chartData.[0].key').value(calculation.getChartData().get(0).getKey()))
                .andExpect(jsonPath('$.chartData.[0].investmentValue').value(calculation.getChartData().get(0).getInvestmentValue()))
                .andExpect(jsonPath('$.chartData.[0].profit').value(calculation.getChartData().get(0).getProfit()))
                .andExpect(jsonPath('$.chartData.[0].deposit').value(calculation.getChartData().get(0).getDeposit()))
    }

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        CalculatorService calculatorService() {
            return detachedMockFactory.Stub(CalculatorService)
        }
    }
}
