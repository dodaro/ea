package it.unical.inf.ea.jpa.entity.basic;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="ORDER_TABLE")
public class OrderEntry {
 
    @EmbeddedId
    private OrderEntryPK entryId;
 
    // ...
}