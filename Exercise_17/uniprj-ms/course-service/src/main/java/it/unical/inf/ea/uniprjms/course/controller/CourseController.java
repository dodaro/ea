package it.unical.inf.ea.uniprjms.course.controller;

import it.unical.inf.ea.uniprjms.course.service.CourseTeacherService;
import it.unical.inf.ea.uniprjms.shared.dto.course.CourseTeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class CourseController {

  private final CourseTeacherService courseTeacherService;

  @GetMapping("/courses-teachers")
  public ResponseEntity<List<CourseTeacherDto>> all() {
    return ResponseEntity.ok(courseTeacherService.getCourseTeacherDto());
  }

}
