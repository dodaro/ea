package it.unical.inf.ea.jpa.dbtest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "JT_BlogPost")
public class BlogPost extends Publication {
 
    @Column
    private String url;

}
