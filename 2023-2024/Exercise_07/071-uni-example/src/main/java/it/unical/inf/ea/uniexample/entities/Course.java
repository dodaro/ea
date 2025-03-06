package it.unical.inf.ea.uniexample.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    private Teacher teacher;

    @OneToOne
    //@JoinColumn(name = "MATERIAL_ID", referencedColumnName = "ID")
    private CourseMaterial material;

    @ManyToMany
    @JoinTable(
      name = "STUDENTS_COURSES",
      joinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    )
    private List<Student> students = new ArrayList<>();

    public Course(String title) {
        this.title = title;
    }
}