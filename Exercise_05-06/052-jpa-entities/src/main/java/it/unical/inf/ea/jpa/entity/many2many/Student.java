package it.unical.inf.ea.jpa.entity.many2many;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;

@Entity
class Student {
 
    @Id
    Long id;

    /*
        We can do this with the @JoinTable annotation in the Student class.
        We provide the name of the join table (course_like), and the foreign keys
        with the @JoinColumn annotations.
        The joinColumn attribute will connect to the owner side of the relationship,
        and the inverseJoinColumn to the other side:
     */
    @ManyToMany
    @JoinTable(
        name = "course_like",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Course> likedCourses;
 
    // additional properties
    // standard constructors, getters, and setters
}






