package it.unical.inf.ea.uniprjms.teacher.data.dao;

import it.unical.inf.ea.uniprjms.teacher.data.entities.Teacher;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
        return criteriaBuilder.like(root.get("firstName"), "%"+name+"%");
      }
    };
  }
}
