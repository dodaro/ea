package it.unical.inf.ea.dock1;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT")
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

}
