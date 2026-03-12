package it.unical.inf.ea.controller;

import it.unical.inf.ea.dto.StudentDTO;
import it.unical.inf.ea.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller()
public class StudentController {

    @Autowired
    StudentService service;

    public StudentDTO createNewStudent() {
        return service.createNewStudent();
    }
}