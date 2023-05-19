package it.unical.inf.ea.uniprjms.ms.course.controller;

import it.unical.inf.ea.uniprjms.ms.course.data.service.TeacherService;
import it.unical.inf.ea.uniprjms.ms.course.dto.TeacherBasicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/teacher-api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class TeacherController {

  private final TeacherService teacherService;

  @GetMapping("/teachers/{id}")
  public ResponseEntity<TeacherBasicDto> getById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(teacherService.getTeacherBasicDtoById(id));
  }
}
