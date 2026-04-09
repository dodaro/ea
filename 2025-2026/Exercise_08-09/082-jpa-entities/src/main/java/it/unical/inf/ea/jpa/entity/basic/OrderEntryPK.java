package it.unical.inf.ea.jpa.entity.basic;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderEntryPK implements Serializable {
 
    private long orderId;
    private long productId;
 
    // standard constructor, getters, setters
    // equals() and hashCode() 
}