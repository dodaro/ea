package it.unical.inf.ea.uniprj.services;

import it.unical.inf.ea.uniprj.data.service.CourseService;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolHoursService {

  @Autowired
  private StudentService studentService;

  @Autowired
  private CourseService courseService;

  public String make() {
    //...
    return null;
  }
}
