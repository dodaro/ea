package it.unical.inf.ea.jpa.entity.inheritance.joinedtable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "JT_Book")
public class Book extends Publication {
 
    @Column
    private int pages;
 
}