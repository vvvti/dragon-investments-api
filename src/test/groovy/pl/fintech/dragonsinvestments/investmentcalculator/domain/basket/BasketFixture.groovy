package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket

class BasketFixture {

    static final UUID BASKET_ID = UUID.randomUUID()

    static final Basket BASKET = new Basket(BASKET_ID, BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)

    static final BasketDto BASKET_DTO = new BasketDto(BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)

    static final BasketResult BASKET_RESULT = new BasketResult(BASKET_ID, RiskType.AGGRESSIVE, Currency.EUR,
            BigDecimal.TEN, BigDecimal.TEN, new Profit(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.TEN))
}
