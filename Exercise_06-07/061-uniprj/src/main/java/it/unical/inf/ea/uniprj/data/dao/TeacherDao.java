package it.unical.inf.ea.uniprj.data.dao;

import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.Student;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

  default Specification<Teacher> theLastFilter(String... names) {
    return (Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
      ListJoin<Teacher, Course> courses = root.joinList("courses");
      ListJoin<Course, Student> students = courses.joinList("students");

      return students.get("firstName").in(names);
    };
  }

}
