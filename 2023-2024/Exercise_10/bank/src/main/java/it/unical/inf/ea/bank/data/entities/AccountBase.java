package it.unical.inf.ea.bank.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name="ACCOUNT_BASE")
@Data
public class AccountBase extends Account {

  @Column(name = "POINTS")
  private Integer points;
}
