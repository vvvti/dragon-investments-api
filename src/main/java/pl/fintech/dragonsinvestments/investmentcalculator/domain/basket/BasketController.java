package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fintech.dragonsinvestments.investmentcalculator.utils.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class BasketController {
  private final BasketService basketService;

  @Value("${pl.fintech.app-name}")
  private String applicationName;
  private static final String ENTITY_NAME = "basket";

  @GetMapping("/basket/{id}")
  public BasketResult getBasket(@PathVariable UUID id) {
    log.debug("REST request to get Basket : {}", id);
    return basketService.getBasket(id);
  }

  @PostMapping("/basket")
  public ResponseEntity<BasketResult> createBasket(@RequestBody BasketDto basketDto) throws URISyntaxException {
    log.debug("REST request to save Basket : {}", basketDto);
    BasketResult result = basketService.save(basketDto);
    return ResponseEntity.created(new URI("/api/basket/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  @PutMapping("/basket")
  public ResponseEntity<BasketResult> updateBasket(@RequestBody BasketDto basketDto) {
    log.debug("REST request to update Basket : {}", basketDto);
    BasketResult result = basketService.save(basketDto);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
        .body(result);
  }
}
