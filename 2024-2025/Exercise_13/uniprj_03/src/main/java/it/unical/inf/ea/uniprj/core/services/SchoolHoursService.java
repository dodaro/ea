package it.unical.inf.ea.uniprj.core.services;

import it.unical.inf.ea.uniprj.data.service.CourseService;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolHoursService {

  private final StudentService studentService;

  private final CourseService courseService;
  
  // private final dlvApi;

  public String make() {
    //...
    return null;
  }
}
