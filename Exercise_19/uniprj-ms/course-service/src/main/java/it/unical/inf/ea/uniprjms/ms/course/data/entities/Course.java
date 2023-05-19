package it.unical.inf.ea.uniprjms.ms.course.data.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @Column(name = "TEACHER_ID")
    private Long teacherId;

    @OneToOne(mappedBy = "course", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private CourseMaterial material;

    public Course(String title) {
        this.title = title;
    }
}