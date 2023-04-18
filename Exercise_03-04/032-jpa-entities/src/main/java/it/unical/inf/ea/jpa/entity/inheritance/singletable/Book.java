package it.unical.inf.ea.jpa.entity.inheritance.singletable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "ST_Book")
@DiscriminatorValue("Book")
public class Book extends Publication {
 
    @Column
    private int pages;
}