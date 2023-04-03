package it.unica.inf.ea.bank.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Entity(name="ACCOUNT_PREMIUM")
@Data
public class AccountPremium extends Account {

  public enum Promotion {
    PROMO_1, PROMO_2, PROMO_3;
  }

  @Column(name = "POINTS")
  private Integer points;

  @Column(name = "PROMOTION")
  @Enumerated(EnumType.STRING)
  private Promotion promotion;

}
