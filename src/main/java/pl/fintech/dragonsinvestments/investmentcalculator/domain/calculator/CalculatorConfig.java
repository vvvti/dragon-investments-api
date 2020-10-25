package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorConfig {

    @Bean
    public CalculatorService calculatorService() {
        return new CalculatorService(new InvestmentCalculator());
    }
}
