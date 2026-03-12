package it.unical.ea.library.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Inheritance(strategy =  InheritanceType.JOINED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 13)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "idLibrary")
    private Library library;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

}
