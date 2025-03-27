package it.unical.ea.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class EBook extends Book {

    @Column(length = 200)
    private String downloadUrl;

}
