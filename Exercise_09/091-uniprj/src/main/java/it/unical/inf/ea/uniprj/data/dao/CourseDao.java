package it.unical.inf.ea.uniprj.data.dao;

import it.unical.inf.ea.uniprj.data.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDao extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

  @Query("select t.title from Course t where t.id=:id")
  String findTitleById(@Param("id") Long id);

  Optional<Course> findByTitle(String title);

  List<Course> findAllByTeacherFirstName(String name);

  @Query("select c from Course c where c.teacher.firstName=:name")
  List<Course> getCoursesByTeacherName(@Param("name") String name);

}
