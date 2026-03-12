package it.unical.inf.ea.jpa.entity.one2many.m2m;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
class Student {

    @Id
    private Long id;

    // ...
 
    @OneToMany(mappedBy = "student")
    Set<CourseRegistration> registrations;
 
    // ...
}