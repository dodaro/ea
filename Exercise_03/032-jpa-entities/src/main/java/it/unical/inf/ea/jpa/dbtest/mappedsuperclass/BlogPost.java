package it.unical.inf.ea.jpa.dbtest.mappedsuperclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name = "MS_BlogPost")
public class BlogPost extends Publication {
 
    @Column
    private String url;
 
}