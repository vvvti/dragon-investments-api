package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fintech.dragonsinvestments.investmentcalculator.utils.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
class BasketController {
  private final BasketService basketService;

  @Value("${pl.fintech.app-name}")
  private String applicationName;
  private static final String ENTITY_NAME = "basket";

  @Operation(summary = "Get result of basket calculation by id")
  @GetMapping("/basket/{id}")
  BasketResult getBasket(@PathVariable UUID id) {
    log.debug("REST request to get Basket : {}", id);
    return basketService.getBasket(id);
  }

  @Operation(summary = "Save result of basket calculation")
  @PostMapping("/basket")
  ResponseEntity<BasketResult> createBasket(@RequestBody @Valid BasketDto basketDto) throws URISyntaxException {
    log.debug("REST request to create Basket : {}", basketDto);
    BasketResult result = basketService.create(basketDto);
    return ResponseEntity.created(new URI("/api/basket/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  @Operation(summary = "Update result of basket calculation")
  @PutMapping("/basket/{id}")
  ResponseEntity<BasketResult> updateBasket(@PathVariable UUID id, @RequestBody @Valid BasketDto basketDto) {
    log.debug("REST request to update Basket with id: {} and : {}", id, basketDto);
    BasketResult result = basketService.update(id, basketDto);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
        .body(result);
  }
}
