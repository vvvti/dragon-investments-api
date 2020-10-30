package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CalculatorConfig {

    @Bean
    CalculatorService calculatorService() {
        return new CalculatorService(new InvestmentCalculator());
    }
}
