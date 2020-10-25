package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BasketConfig {

    @Bean
    BasketService basketService(BasketRepository basketRepository) {
        return new BasketService(basketRepository, new BasketProfitCalculator(), new BasketResultAssembler());
    }
}
