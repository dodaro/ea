package it.unical.inf.ea.jpa.entity.many2many.compositekey;

import it.unical.inf.ea.jpa.entity.basic.Student;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
class CourseRating {
 
    @EmbeddedId
    CourseRatingKey id;
 
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;
 
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;
 
    int rating;
    
    // standard constructors, getters, and setters
}