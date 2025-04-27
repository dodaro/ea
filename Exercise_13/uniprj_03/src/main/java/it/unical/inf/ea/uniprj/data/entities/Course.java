package it.unical.inf.ea.uniprj.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
// Sovrascrive l'operazione DELETE: invece di cancellare, imposta deleted=1
@SQLDelete(sql = "UPDATE COURSE SET deleted = 1 WHERE id=?")
// Aggiunge automaticamente "deleted = 0" a tutte le query SELECT per questa entità
@SQLRestriction("deleted = 0") // NUOVA ANNOTAZIONE
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", unique = true)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    private Teacher teacher;

    @OneToOne(mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private CourseMaterial material;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    @Version
    private int version; // Questo campo viene usato per la concorrenza ottimistica

    @Column(name = "deleted", nullable = false)
    private int deleted = 0; // usato con @SQLDelete e @SQLRestriction definite sulla classe

    public Course(String title) {
        this.title = title;
    }
}