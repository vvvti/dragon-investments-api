package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BasketService {
  private final BasketRepository basketRepository;

  public BasketResult getBasket(UUID id) {
    return BasketCalculator.calculation(basketRepository.findById(id).get());
  }

  public BasketResult save(BasketDto basketDto) {
    Basket def = new Basket();
    if (basketDto.getId() != null) {
      Optional<Basket> basket = basketRepository.findById(basketDto.getId());
      if (basket.isPresent()) {
        def = basket.get();
      }
    }

    def.setRiskType(basketDto.getRiskType());
    def.setValue(basketDto.getValue());
    basketRepository.save(def);
    return BasketCalculator.calculation(def);
  }
}
