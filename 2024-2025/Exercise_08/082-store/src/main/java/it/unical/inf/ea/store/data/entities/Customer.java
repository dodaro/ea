package it.unical.inf.ea.store.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Data
@NoArgsConstructor
public class Customer {

  @Id
  @GeneratedValue
  private Long id;

  @Basic(optional = false)
  @Column(name = "FISCAL_CODE", length = 16, unique = true)
  private String fiscalCode;

  @Basic(optional = false)
  @Column(name = "FIRSTNAME", length = 255)
  private String firstname;

  @Basic(optional = false)
  @Column(name = "lastNAME", length = 255)
  private String lastname;

  @Column(name = "BIRTH_DATE")
  private LocalDate birthdate;

  @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
  private List<Coupon> coupons;

  @ManyToMany
  @JoinTable(
      name = "PURCHASE",
      joinColumns = @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
  )
  private List<Product> products;
}
