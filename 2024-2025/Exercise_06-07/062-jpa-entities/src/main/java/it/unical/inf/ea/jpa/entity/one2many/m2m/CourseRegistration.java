package it.unical.inf.ea.jpa.entity.one2many.m2m;

import it.unical.inf.ea.jpa.entity.basic.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
class CourseRegistration {
 
    @Id
    Long id;
 
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;
 
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;
 
    LocalDateTime registeredAt;
 
    int grade;
    
    // additional properties
    // standard constructors, getters, and setters
}