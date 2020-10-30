package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import pl.fintech.dragonsinvestments.investmentcalculator.PostgreSQLContainerSpecification
import spock.lang.Title

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Title("Calculation functional test")
@ActiveProfiles("Test")
@SpringBootTest
@AutoConfigureMockMvc
class CalculatorFT extends PostgreSQLContainerSpecification {

    @Autowired
    MockMvc mockMvc

    def 'GET /calculator should return correct calculator result dto'() {

        given: 'Mock calculatorService get'
        List<PeriodCalculationDto> period = new ArrayList<>()
        period.add(new PeriodCalculationDto(
                0,
                BigDecimal.valueOf(1000.00),
                BigDecimal.valueOf(0.00),
                BigDecimal.valueOf(1000.00)))

        period.add(new PeriodCalculationDto(
                1,
                BigDecimal.valueOf(2215.65),
                BigDecimal.valueOf(15.65),
                BigDecimal.valueOf(2200.00)))

        def calculationResult = new CalculationResultDto(
                BigDecimal.valueOf(2215.65),
                BigDecimal.valueOf(15.65),
                BigDecimal.valueOf(2200.00),
                period)

        expect: 'GET /calculator returned json with correct calculator result dto'
        mockMvc.perform(get('/calculator')
                .param("initialValue", "1000")
                .param("monthlySaving", "100")
                .param("savingPeriod", "1")
                .param("annualProfit", "1")
                .param("paymentFrequency", "12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.finalValue').value(calculationResult.getFinalValue()))
                .andExpect(jsonPath('$.estimatedProfit').value(calculationResult.getEstimatedProfit()))
                .andExpect(jsonPath('$.depositValue').value(calculationResult.getDepositValue()))
                .andExpect(jsonPath('$.chartData.[0].key').value(calculationResult.getChartData().get(0).getKey()))
                .andExpect(jsonPath('$.chartData.[0].investmentValue').value(calculationResult.getChartData().get(0).getInvestmentValue()))
                .andExpect(jsonPath('$.chartData.[0].profit').value(calculationResult.getChartData().get(0).getProfit()))
                .andExpect(jsonPath('$.chartData.[0].deposit').value(calculationResult.getChartData().get(0).getDeposit()))
                .andExpect(jsonPath('$.chartData.[1].key').value(calculationResult.getChartData().get(1).getKey()))
                .andExpect(jsonPath('$.chartData.[1].investmentValue').value(calculationResult.getChartData().get(1).getInvestmentValue()))
                .andExpect(jsonPath('$.chartData.[1].profit').value(calculationResult.getChartData().get(1).getProfit()))
                .andExpect(jsonPath('$.chartData.[1].deposit').value(calculationResult.getChartData().get(1).getDeposit()))
    }
}
