package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
class BasketService {
    private final BasketRepository basketRepository;
    private final BasketProfitCalculator basketProfitCalculator;
    private final BasketResultAssembler dtoAssembler;

    BasketResult getBasket(UUID id) {
        Basket basket = basketRepository.getOne(id);
        return prepareResult(basket);
    }

    BasketResult update(UUID id, BasketDto basketDto) {
        Basket basket = basketRepository.getOne(id);
        basket.setRiskType(basketDto.getRiskType());
        basket.setValue(basketDto.getValue());
        basket.setCurrency(basketDto.getCurrency());
        basketRepository.save(basket);
        return prepareResult(basket);
    }

    BasketResult create(BasketDto basketDto) {
        Basket basket = new Basket(basketDto.getValue(), basketDto.getCurrency(), basketDto.getRiskType());
        basketRepository.save(basket);
        return prepareResult(basket);
    }

    private BasketResult prepareResult(Basket basket) {
        BasketProfitCalculation calculation = basketProfitCalculator.calculate(basket);
        return dtoAssembler.toResult(basket, calculation);
    }
}
