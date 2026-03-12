package it.unical.inf.ea.jpa.entity.inheritance.tableperclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "TC_Book")
public class Book extends Publication {
 
    @Column
    private int pages;
}