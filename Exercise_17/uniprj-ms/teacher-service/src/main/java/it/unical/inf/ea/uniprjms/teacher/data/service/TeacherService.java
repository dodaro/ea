package it.unical.inf.ea.uniprjms.teacher.data.service;

import it.unical.inf.ea.uniprjms.teacher.data.entities.Teacher;
import it.unical.inf.ea.uniprjms.domain.dto.TeacherBasicDto;
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
