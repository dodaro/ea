package it.unical.inf.ea.uniprj.data.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STUDENT")
@Data
@NoArgsConstructor
public class Student {

    public enum Gender {
        MALE,
        FEMALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)  // blocked in The database layer
    @Column(name = "LASTNAME") // blocked at runtime and before contacting the database
    private String lastName;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "EXAM_AVERAGE_GRADE")
    private double examAverageGrade;

    @Column(name = "BIRTHDATE")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "WANT_NEWSLETTER")
    private boolean wantsNewsletter;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "ST_STREET")),
        @AttributeOverride(name = "number", column = @Column(name = "ST_NUMBER")),
        @AttributeOverride(name = "city", column = @Column(name = "ST_CITY"))
    })
    private Address address;

    @ManyToMany
    @JoinTable(
        name = "STUDENTS_COURSES",
        joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    )
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}
