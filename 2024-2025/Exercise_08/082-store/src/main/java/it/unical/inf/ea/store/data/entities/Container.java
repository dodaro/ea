package it.unical.inf.ea.store.data.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CONTAINER")
@Data
@NoArgsConstructor
public class Container {

  @Id
  @GeneratedValue
  private Long id;

  @Basic(optional = false)
  @Column(name = "NAME", length = 255, unique = true)
  private String name;

  @Column(name = "COLOR", length = 255)
  private String color;

  @OneToOne(mappedBy = "container")
  private Product product;
}
