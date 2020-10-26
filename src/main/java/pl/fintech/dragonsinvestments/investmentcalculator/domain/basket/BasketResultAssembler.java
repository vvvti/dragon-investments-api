package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class BasketResultAssembler {

    BasketResult toResult(Basket basket, BasketProfitCalculation finalBasketValue) {
        return BasketResult.builder()
                .id(basket.getId())
                .basketValue(basket.getValue())
                .riskType(basket.getRiskType())
                .currency(basket.getCurrency())
                .totalAmount(
                        finalBasketValue.getFinalBasketValue())
                .profit(new Profit(
                        finalBasketValue.getFinalCash().subtract(basket.cashValue()),
                        finalBasketValue.getFinalBonds().subtract(basket.bondsValue()),
                        finalBasketValue.getFinalStock().subtract(basket.stocksValue())))
                .build();
    }


}
