package it.unical.inf.ea.uniprj.core.services;

import it.unical.inf.ea.uniprj.data.dto.StudentBasicDto;
import it.unical.inf.ea.uniprj.data.dto.Thesis;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThesisService {

  private final StudentService studentService;

  private final TeacherService teacherService;

  private final ModelMapper modelMapper;

  public Thesis generateThesis(String title, Long studentId) {
    Teacher teacher = teacherService.getAllTeacher().get(0);
    StudentBasicDto student = studentService.getById(studentId);
    Thesis thesis = modelMapper.map(teacher, Thesis.class);
    modelMapper.map(student, thesis);
    thesis.setTitle(title);
    return thesis;
  }

}
