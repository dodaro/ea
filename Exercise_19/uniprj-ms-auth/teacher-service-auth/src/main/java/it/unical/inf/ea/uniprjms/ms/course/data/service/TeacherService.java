package it.unical.inf.ea.uniprjms.ms.course.data.service;

import it.unical.inf.ea.uniprjms.ms.course.data.entities.Teacher;
import it.unical.inf.ea.uniprjms.ms.course.dto.TeacherBasicDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface TeacherService {
  List<Teacher> getAllTeacher();

  void add(Teacher teacher);

  Teacher save(Teacher teacher);

  TeacherBasicDto getTeacherBasicDtoById(Long id);

  Teacher getTeacherById(Long id);

  List<Teacher> getAllTeacher(Specification<Teacher> aaa);
}
