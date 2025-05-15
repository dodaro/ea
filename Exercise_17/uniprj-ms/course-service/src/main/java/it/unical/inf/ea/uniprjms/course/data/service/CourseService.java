package it.unical.inf.ea.uniprjms.course.data.service;

import it.unical.inf.ea.uniprjms.course.data.entities.Course;
import it.unical.inf.ea.uniprjms.domain.dto.course.CourseTeacherDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CourseService {

  Course getById(Long id);

  Course save(Course course);

  Optional<Course> getByTitle(String title);

  String getTitleById(long id);

  Collection<Course> getAll(Specification<Course> spec);

  Collection<Course> getAll();

  List<CourseTeacherDto> getCourseTeacherDto();
}
