package it.unical.inf.ea.uniprjms.teacher.controller;

import it.unical.inf.ea.uniprjms.teacher.data.service.TeacherService;
import it.unical.inf.ea.uniprjms.domain.dto.TeacherBasicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class TeacherController {

  private final TeacherService teacherService;

  @GetMapping("/teachers/{id}")
  public ResponseEntity<TeacherBasicDto> getById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(teacherService.getTeacherBasicDtoById(id));
  }
}
