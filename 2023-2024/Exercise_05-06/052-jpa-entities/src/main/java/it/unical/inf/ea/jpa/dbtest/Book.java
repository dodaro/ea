package it.unical.inf.ea.jpa.dbtest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "JT_Book")
public class Book extends Publication {
 
    @Column
    private int pages;
 
}