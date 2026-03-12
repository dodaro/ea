package it.unical.inf.ea.jpa.entity.inheritance.joinedtable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "JT_BlogPost")
public class BlogPost extends Publication {
 
    @Column
    private String url;

}
