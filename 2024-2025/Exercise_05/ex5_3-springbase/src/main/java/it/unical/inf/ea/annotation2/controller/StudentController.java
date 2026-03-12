package it.unical.inf.ea.annotation2.controller;

import it.unical.inf.ea.annotation2.dto.StudentDTO;
import it.unical.inf.ea.annotation2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("studentController")
public class StudentController {

  @Autowired
  private StudentService service;

  public StudentDTO createNewStudent() {
    return service.createNewStudent();
  }
}
