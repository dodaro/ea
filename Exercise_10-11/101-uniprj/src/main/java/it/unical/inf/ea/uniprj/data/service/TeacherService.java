package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.dto.TeacherBasicDto;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface TeacherService {
  List<Teacher> getAllTeacher();

  void deleteTeacherAndEmptyCourse();

  void add(Teacher teacher);

  Teacher save(Teacher teacher);

  TeacherBasicDto getTeacherBasicDtoById(Long id);

  Teacher getTeacherById(Long id);

  List<Teacher> getAllTeacherByLastFilter(String... names);

  List<Teacher> getAllTeacher(Specification<Teacher> aaa);
}
