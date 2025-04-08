package it.unical.inf.ea.uniprj.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

    public Course(String title) {
        this.title = title;
    }
}