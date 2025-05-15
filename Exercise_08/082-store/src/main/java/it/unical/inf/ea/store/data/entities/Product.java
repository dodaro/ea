package it.unical.inf.ea.store.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Data
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  @Basic(optional = false)
  @Column(name = "NAME", length = 255)
  private String name;

  @Basic(optional = false)
  @Column(name = "BRAND", length = 255)
  private String brand;

  @Basic(optional = false)
  @Column(name = "BAR_CODE", length = 50, unique = true)
  private String barCode;

  @Column(name = "PRICE")
  private Double price;

  @Lob //large object
  @Basic(optional = false)
  @Column(name = "DESCRIPTION", length = 1024)
  private String description;

  @Column(name = "PHOTO")
  private byte[] photo;

  @OneToOne
  @JoinColumn(name = "CONTAINER_ID", referencedColumnName = "ID")
  private Container container;

  @ManyToMany(mappedBy = "products")
  private List<Customer> customers;
}
