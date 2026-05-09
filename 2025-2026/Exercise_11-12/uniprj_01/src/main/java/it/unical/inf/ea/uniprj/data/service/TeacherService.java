package it.unical.inf.ea.uniprj.data.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import it.unical.inf.ea.uniprj.data.entities.Teacher;

public interface TeacherService {
  List<Teacher> getAllTeacher();

  void deleteTeacherAndEmptyCourse();

  void add(Teacher teacher);

  Teacher save(Teacher teacher);

  Teacher getById(Long valueOf);

  List<Teacher> getAllTeacherByLastFilter(String... names);

  List<Teacher> getAllTeacher(Specification<Teacher> aaa);

  void testLazy(long id);
}
