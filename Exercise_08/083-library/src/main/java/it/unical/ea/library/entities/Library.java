package it.unical.ea.library.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "library")
    private Set<Book> books;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;


}
