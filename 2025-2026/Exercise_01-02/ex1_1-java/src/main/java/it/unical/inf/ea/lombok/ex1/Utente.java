package it.unical.inf.ea.lombok.ex1;

import java.util.Objects;

public class Utente {

    private String username;

    private String password;

    private String email;

    public Utente() {
        //empty
    }

    public Utente(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(username, utente.username) && Objects.equals(password, utente.password) && Objects.equals(email, utente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email);
    }
}
