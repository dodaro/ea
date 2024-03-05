package it.unica.inf.ea.bank.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Credential {

  @Column(name="USERNAME", unique = true)
  private String username;

  @Column(name="PASSWORD")
  private String password;

}
