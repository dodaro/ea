package it.unical.inf.ea.uniprj.data.dao;

import it.unical.inf.ea.uniprj.dto.Gender;
import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.Student;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

// Dynamic Queries With Specifications
public class UniSpecification {

  @Data
  public static class Filter {
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;

  }

  private UniSpecification() {
  }

  public static Specification<Student> mySpec() {
    return new Specification<Student>() {
      @Override
      public Predicate toPredicate(Root<Student> root,
                                   CriteriaQuery<?> criteriaQuery,
                                   CriteriaBuilder criteriaBuilder) {
        return null;
      }
    };
  }

  public static Specification<Student> withFilter2(Filter filter) {
    return (Specification<Student>) (
        Root<Student> root,
        CriteriaQuery<?>criteriaQuery,
        CriteriaBuilder criteriaBuilder) -> {

      // where id is not null or firstName=filter.getfirstname and lastname=filter.getLastName
      //and birtDate>=date
      Predicate predicate = criteriaBuilder.isNotNull(root.get("id"));

      if (filter.getFirstName() != null)
        predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(root.get("firstName"), filter.getFirstName()));
      if (filter.getLastName() != null)
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("firstName"), filter.getLastName()));
      if (filter.getAge() != null) {
        LocalDate date = LocalDate.now().minus(filter.getAge(), ChronoUnit.YEARS);
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("birthDate"), date));
      }

      return criteriaQuery.where(predicate)//
          .distinct(true) //
          .orderBy(criteriaBuilder.desc(root.get("firstName")))//
          .getRestriction();
    };
  }

  public static Specification<Student> withFilter(Filter filter) {
    return new Specification<Student>() {
      @Override
      public Predicate toPredicate(Root<Student> root,
          CriteriaQuery<?> criteriaQuery,
          CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getFirstName() != null)
          predicates.add(criteriaBuilder.equal(root.get("firstName"), filter.getFirstName()));
        if (filter.getLastName() != null)
          predicates.add(criteriaBuilder.equal(root.get("lastName"), filter.getLastName()));
        if (filter.getAge() != null) {
          LocalDate date = LocalDate.now().minus(filter.getAge(), ChronoUnit.YEARS);
          predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthDate"), date));
        }

        if (predicates.isEmpty())
          predicates.add(criteriaBuilder.equal(root.get("id"), -1L)); // passami almeno un filtro

        return criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])))//
            .distinct(true) //
            .orderBy(criteriaBuilder.desc(root.get("firstName")))//
            .getRestriction();
      }
    };
  }

  public static Specification<Course> anotherFilter(String... names) {
    return (Specification<Course>) (root, criteriaQuery, criteriaBuilder) -> {
      Predicate predicate = criteriaBuilder.conjunction();

      predicate = criteriaBuilder.and(predicate, root.join("teacher").get("lastName")).in(names);
      return  criteriaQuery.where(predicate).getRestriction();
    };
  }

  public static Specification<Teacher> theLastFilter(String... names) {
    return (Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
      ListJoin<Teacher, Course> courses = root.joinList("courses");
      ListJoin<Course, Student> students = courses.joinList("students");

      return students.get("firstName").in(names);
    };
  }
}







