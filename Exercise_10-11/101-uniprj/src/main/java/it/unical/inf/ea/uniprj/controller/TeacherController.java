package it.unical.inf.ea.uniprj.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {
}
