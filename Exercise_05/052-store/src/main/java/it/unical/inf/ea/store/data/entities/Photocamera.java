package it.unical.inf.ea.store.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("P")
@Data
@NoArgsConstructor
class Photocamera extends Product {

  @Column(name="SENSOR_SIZE")
  private String sensorSize;
}
