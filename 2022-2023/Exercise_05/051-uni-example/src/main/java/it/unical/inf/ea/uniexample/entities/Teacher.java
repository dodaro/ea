package it.unical.inf.ea.uniexample.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Teacher")
@Data
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="FIRSTNAME")
    private String firstName;

    @Column(name="LASTNAME")
    private String lastName;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<Course> courses;
}