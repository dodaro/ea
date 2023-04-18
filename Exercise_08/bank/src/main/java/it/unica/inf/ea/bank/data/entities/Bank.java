package it.unica.inf.ea.bank.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "BANK")
@Data
public class Bank {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private Long id;

  @Basic(optional = false)
  @Column(name="NAME", unique = true)
  private String name;

  @Column(name="LOCATION")
  private String location;

  @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
  private List<Customer> customers = new ArrayList<>();

  @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
  private List<Teller> tellers = new ArrayList<>();

  @Transient
  public Integer numCosenzaBank;
}
