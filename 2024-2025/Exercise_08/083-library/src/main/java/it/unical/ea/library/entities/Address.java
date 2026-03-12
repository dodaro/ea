package it.unical.ea.library.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String street;

    @Column(length = 50)
    private String city;

    @OneToOne(mappedBy = "address")
    private Library library;

}
