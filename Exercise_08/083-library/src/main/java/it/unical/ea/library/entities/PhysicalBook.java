package it.unical.ea.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PhysicalBook extends Book {

    @Column(length = 50)
    private String shelfLocation;

}
