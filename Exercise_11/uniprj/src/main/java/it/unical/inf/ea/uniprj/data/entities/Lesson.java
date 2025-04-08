package it.unical.inf.ea.uniprj.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
public class Lesson {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "TITLE", unique = true)
    private String title;


    @ManyToOne
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    private Teacher teacher;

}
