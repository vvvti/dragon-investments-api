package pl.fintech.dragonsinvestments.investmentcalculator.domain.basket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "t_basket")
@Getter
@Setter
@AllArgsConstructor
public class Basket {

  @Id
  private UUID id;

  @NotNull
  @Column(name = "value")
  private BigDecimal value;

  @NotNull
  @Column(name = "risk_type")
  @Enumerated(EnumType.STRING)
  RiskType riskType;

  Basket() {
    this.id = UUID.randomUUID();
  }
}
