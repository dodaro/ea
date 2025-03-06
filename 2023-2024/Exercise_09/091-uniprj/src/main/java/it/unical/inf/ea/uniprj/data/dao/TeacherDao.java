package it.unical.inf.ea.uniprj.data.dao;

import it.unical.inf.ea.uniprj.data.entities.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

  default Specification<Teacher> nameLike(String name){
    return new Specification<Teacher>() {

      @Override
      public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        //return criteriaBuilder.like(root.get("firstName"), "%"+name+"%");
        return criteriaBuilder.like(root.get(Teacher_.FIRST_NAME), "%"+name+"%");
      }
    };
  }


  default Specification<Teacher> theLastFilter(String... names) {
    return (Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
      // ListJoin<Teacher, Course> courses = root.joinList("courses");
      ListJoin<Teacher, Course> courses = root.joinList(Teacher_.COURSES);
     // ListJoin<Course, Student> students = courses.joinList("students");
      ListJoin<Course, Student> students = courses.joinList(Course_.STUDENTS);

      return students.get("firstName").in(names);
    };
  }




  default Specification<Course> isPremium(String nameCourseStart, String nameTeacherStart) {
    return (root, query, criteriaBuilder) -> {

      //Join<Course, Teacher> teacherCourses = root.join("courses");
      Join<Course, Teacher> teacherCourses = root.join(Teacher_.COURSES);

      return criteriaBuilder.and( //
          // criteriaBuilder.like(root.get("firstName"), "%" + nameTeacherStart), //
          criteriaBuilder.like(root.get(Teacher_.FIRST_NAME), "%" + nameTeacherStart), //
          // criteriaBuilder.like(teacherCourses.get("title"), "%" + nameCourseStart)
          criteriaBuilder.like(teacherCourses.get(Course_.TITLE), "%" + nameCourseStart)
      );
    };
  }

}
