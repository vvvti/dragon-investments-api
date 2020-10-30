package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import pl.fintech.dragonsinvestments.investmentcalculator.PostgreSQLContainerSpecification
import spock.lang.Subject


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BasketRepositoryIT extends PostgreSQLContainerSpecification {

    @Subject
    @Autowired
    BasketRepository repository

    def cleanup() {
        repository.deleteAll()
    }

    def 'Should store new basket'() {
        given:
            def basket = new Basket(UUID.randomUUID(), BigDecimal.TEN, Currency.EUR, RiskType.AGGRESSIVE)

        when:
            repository.save(basket)

        then:
            def fromDb = repository.findAll()
            fromDb.size() == 1

        and:
            with(fromDb.first()) {
                id == basket.id
                value == basket.value
                currency == basket.currency
                riskType == basket.riskType
            }
    }
}
