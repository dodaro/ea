package it.unical.inf.ea.uniprj.core.services;

import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import it.unical.inf.ea.uniprj.dto.StudentBasicDto;
import it.unical.inf.ea.uniprj.dto.Thesis;
import it.unical.inf.ea.uniprj.exception.ThesisException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisService {

  private final StudentService studentService;

  private final TeacherService teacherService;

  private final ModelMapper modelMapper;

  @SneakyThrows
  public Thesis generateThesis(String title, Long studentId) {
    if (StringUtils.isBlank(title) || title.length() < 5)
      throw new ThesisException("Title is blank or don't containt at least 5 character");
    Teacher teacher = teacherService.getAllTeacher().get(0);
    StudentBasicDto student = studentService.getById(studentId);
    Thesis thesis = modelMapper.map(teacher, Thesis.class);
    modelMapper.map(student, thesis);
    thesis.setTitle(title);
    return thesis;
  }

}
