package it.unical.inf.ea.jpa.entity.inheritance.tableperclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "TC_BlogPost")
public class BlogPost extends Publication {
 
    @Column
    private String url;
 
}