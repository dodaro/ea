package it.unical.inf.ea.jpa.entity.many2many.compositekey;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
class Student {

    @Id
    Long id;

    // ...
 
    @OneToMany(mappedBy = "student")
    Set<CourseRating> ratings;
 
    // ...
}