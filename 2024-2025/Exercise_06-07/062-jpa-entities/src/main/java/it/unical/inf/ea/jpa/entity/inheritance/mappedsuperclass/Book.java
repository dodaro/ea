package it.unical.inf.ea.jpa.entity.inheritance.mappedsuperclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "MS_Book")
public class Book extends Publication {
 
    @Column
    private int pages;
}