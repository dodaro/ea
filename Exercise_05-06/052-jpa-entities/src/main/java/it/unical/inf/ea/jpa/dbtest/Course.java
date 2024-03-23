package it.unical.inf.ea.jpa.dbtest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
class Course {

    @Id
    Long id;

    /*
    On the target side, we only have to provide the name of the field,
    which maps the relationship.
    Therefore, we set the mappedBy attribute of the @ManyToMany annotation in the Course class:
     */
    @ManyToMany(mappedBy = "likedCourses")
    Set<Student> likes;

    // additional properties
    // standard constructors, getters, and setters
}