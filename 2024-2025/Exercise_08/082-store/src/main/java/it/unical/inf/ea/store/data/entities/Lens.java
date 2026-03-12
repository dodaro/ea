package it.unical.inf.ea.store.data.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("L")
class Lens extends Product {

}
