package it.unical.inf.ea.jpa.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    private Teacher teacher;

    @OneToOne(mappedBy = "course")
    private CourseMaterial material;

    @ManyToMany
    @JoinTable(
      name = "STUDENTS_COURSES",
      joinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    )
    private List<Student> students = new ArrayList<>();

    public Course(String title) {
        this.title = title;
    }

    public Course() {

    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public Teacher teacher() {
        return teacher;
    }

    public CourseMaterial material() {
        return material;
    }

    public List<Student> students() {
        return students;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addStudent(Student student) {
        this.students.add(student);
//        student.addCourse(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}