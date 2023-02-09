package it.unical.demacs.informatica.springsession.domain;

import jakarta.persistence.*;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAccount() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
