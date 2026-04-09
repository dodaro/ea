package it.unical.inf.ea.jpa.entity.many2many.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class CourseRatingKey {
/*
    we used @EmbeddedId, to mark the primary key, which is an instance of the CourseRatingKey class
    we marked the student and course fields with @MapsId
    @MapsId means that we tie those fields to a part of the key, and they're the foreign keys of a many-to-one relationship.
    We need it, because as we mentioned above, in the composite key we can't have entities.
 */

    @Column(name = "student_id")
    Long studentId;
 
    @Column(name = "course_id")
    Long courseId;
 
    // standard constructors, getters, and setters
    // hashcode and equals implementation
}