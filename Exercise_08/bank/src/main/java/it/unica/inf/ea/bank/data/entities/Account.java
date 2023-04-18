package it.unica.inf.ea.bank.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT", uniqueConstraints =
  @UniqueConstraint(columnNames = {"REGION_CODE", "RSS_NO"} ))
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Account {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private Long id;

  @Basic(optional = false)
  @Column(name = "DATE")
  private LocalDateTime creationDate;

  @Basic(optional = false)
  @Column(name = "REGION_CODE")
  private Integer regionCode;

  @Basic(optional = false)
  @Column(name = "RSS_NO")
  private String rssNo;

  @Embedded
  private Credential credential;

  @OneToOne
  private Customer customer;
}
