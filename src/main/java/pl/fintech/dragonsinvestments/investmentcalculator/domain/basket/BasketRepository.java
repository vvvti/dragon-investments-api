package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface BasketRepository extends JpaRepository<Basket, UUID> {
}
