package it.unical.inf.ea.uniprjms.ms.teacher.controller;

import it.unical.inf.ea.uniprjms.ms.teacher.data.service.TeacherService;
import it.unical.inf.ea.uniprjms.ms.teacher.dto.TeacherBasicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
