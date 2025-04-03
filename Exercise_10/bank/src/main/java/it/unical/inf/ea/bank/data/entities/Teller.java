package it.unical.inf.ea.bank.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="TELLER")
@Data
public class Teller {

  @Id
  @GeneratedValue
  @Column(name="ID")
  private Long id;

  @Basic(optional = false)
  @Column(name ="FIRSTNAME", length = 50)
  private String firstname;

  @Basic(optional = false)
  @Column(name ="LASTNAME", length = 50)
  private String lastname;

  @Basic(optional = false)
  @Column(name ="VAT_NUMBER", unique = true)
  private String vatNumber;

  @ManyToOne
  @JoinColumn(name = "BANK_ID")
  private Bank bank;

  @ManyToMany(mappedBy = "tellers")
  private List<Customer> customers;
}
