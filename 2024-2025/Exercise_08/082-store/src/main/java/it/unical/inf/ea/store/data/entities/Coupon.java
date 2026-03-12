package it.unical.inf.ea.store.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "COUPON")
@Data
@NoArgsConstructor
public class Coupon {

  enum CouponStatus {ACTIVE, DISABLED, USED;}

  @Id
  @GeneratedValue
  private Long id;

  @Basic(optional = false)
  @Column(name = "CODE", length = 255)
  private String code;

  @Basic(optional = false)
  @Column(name = "VALUE", length = 255)
  private String value;

  @Enumerated(EnumType.STRING)
  private CouponStatus status;

  @ManyToOne(optional = true)
  @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
  private Customer customer;

  @Basic(optional = false)
  @Column(name = "VALID_FROM")
  private LocalDateTime validFrom;
}
