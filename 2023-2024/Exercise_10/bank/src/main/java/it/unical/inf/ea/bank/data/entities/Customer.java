package it.unical.inf.ea.bank.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="CUSTOMER")
@Data
public class Customer {

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
  @Column(name ="FISCALCODE", unique = true, length = 16)
  private String fiscalcode;

  @Basic(optional = false)
  @Column(name ="ADDRESS", length = 100)
  private String address;

  @Basic(optional = false)
  @Column(name ="BIRTHDATE")
  private LocalDate birthdate;

  @Column(name="ACCT_NO")
  private Integer acctNo;

  @ManyToOne
  @JoinColumn(name="BANK_ID")
  private Bank bank;

  @OneToMany(mappedBy = "customer")
  private List<Loan> loans = new ArrayList<>();

  @ManyToMany
  @JoinTable(name="contract",
    joinColumns = @JoinColumn(name="CUSTOMER_ID"),
    inverseJoinColumns = @JoinColumn(name="TELLER_ID"))
  private List<Teller> tellers;

  @OneToOne
  @JoinColumn(name="ACCOUNT_ID")
  private Account account;
}
