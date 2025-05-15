package it.unical.inf.ea.bank.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="LOAN")
@Data
public class Loan {

  @Id
  @GeneratedValue
  @Column(name="ID")
  private Long id;

  @Basic(optional = false)
  @Column(name = "DATE")
  private LocalDate date;

  @Basic(optional = false)
  @Column(name = "VALUE")
  private Double value;

  @ManyToOne
  @JoinColumn(name="CUSTOMER_ID")
  private Customer customer;
}
