package it.unical.demacs.informatica.posthandlerservice.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String content;
    private Date date;

    public Post() {
    }

    public Post(String username, String content) {
        this.username = username;
        this.content = content;
        this.date = new Date();
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
