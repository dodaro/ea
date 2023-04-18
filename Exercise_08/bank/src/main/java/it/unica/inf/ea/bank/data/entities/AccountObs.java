package it.unica.inf.ea.bank.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name="ACCOUNT_OBS")
@Data
public class AccountObs extends Account {

  @Column(name = "AREA", length = 2)
  private String area;

}
