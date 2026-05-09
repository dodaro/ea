package it.unical.inf.ea.uniprj.services;

import org.springframework.stereotype.Service;

import it.unical.inf.ea.uniprj.data.service.CourseService;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolHoursService {

  private final StudentService studentService;

  private final CourseService courseService;

  public String make() {
    //...
    return null;
  }
}
