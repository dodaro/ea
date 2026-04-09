package it.unical.inf.ea.jpa.entity.inheritance.singletable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "ST_BlogPost")
@DiscriminatorValue("Blog")
public class BlogPost extends Publication {
 
    @Column
    private String url;
 
}