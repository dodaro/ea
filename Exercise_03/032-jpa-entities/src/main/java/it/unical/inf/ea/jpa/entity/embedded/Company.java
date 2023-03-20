package it.unical.inf.ea.jpa.entity.embedded;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Company {
 
    @Id
    @GeneratedValue
    private Integer id;
 
    private String name;
 
    private String address;
 
    private String phone;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride( name = "firstName", column = @Column(name = "contact_first_name")),
        @AttributeOverride( name = "lastName", column = @Column(name = "contact_last_name")),
        @AttributeOverride( name = "phone", column = @Column(name = "contact_phone"))
    })
    private ContactPerson contactPerson;
 
    // standard getters, setters
}








