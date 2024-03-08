package it.unical.demacs.informatica.userhandlerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private boolean consentToContactByEmail;

    public UserAccount() {
    }

    public UserAccount(String username, String email, boolean consentToContactByEmail) {
        this.username = username;
        this.email = email;
        this.consentToContactByEmail = consentToContactByEmail;
    }

    public UserAccount(String username, String email, boolean consentToContactByEmail, Long trackId) {
        this.username = username;
        this.email = email;
        this.consentToContactByEmail = consentToContactByEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isConsentToContactByEmail() {
        return consentToContactByEmail;
    }
}
