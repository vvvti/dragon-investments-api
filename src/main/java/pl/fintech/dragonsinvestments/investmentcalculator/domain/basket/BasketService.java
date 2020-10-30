package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
class BasketService {
    private final BasketRepository basketRepository;
    private final BasketProfitCalculator basketProfitCalculator;
    private final BasketResultAssembler dtoAssembler;

    @Transactional(readOnly = true)
    BasketResult getBasket(UUID id) {
        Basket basket = basketRepository.getOne(id);
        return prepareResult(basket);
    }

    BasketResult update(UUID id, BasketDto basketDto) {
        Basket basket = basketRepository.getOne(id);
        basket.setRiskType(basketDto.getRiskType());
        basket.setValue(basketDto.getBasketValue());
        basket.setCurrency(basketDto.getCurrency());
        return prepareResult(basket);
    }

    BasketResult create(BasketDto basketDto) {
        Basket basket = new Basket(basketDto.getBasketValue(), basketDto.getCurrency(), basketDto.getRiskType());
        basketRepository.save(basket);
        return prepareResult(basket);
    }

    private BasketResult prepareResult(Basket basket) {
        BasketProfitCalculation calculation = basketProfitCalculator.calculate(basket);
        return dtoAssembler.toResult(basket, calculation);
    }
}
