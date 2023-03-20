package it.unical.inf.ea.jpa.entities;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
    private String street;
    private String number;
    private String city;

    public Address() {}

    public Address(String street, String number, String city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) &&
          number.equals(address.number) &&
          city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city);
    }
}