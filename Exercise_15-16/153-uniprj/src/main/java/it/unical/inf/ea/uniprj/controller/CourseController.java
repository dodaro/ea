package it.unical.inf.ea.uniprj.controller;

import it.unical.inf.ea.uniprj.data.service.CourseService;
import it.unical.inf.ea.uniprj.dto.CourseTeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course-api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;

  @GetMapping("/courses-teachers")
  public ResponseEntity<List<CourseTeacherDto>> all() {
    return ResponseEntity.ok(courseService.getCourseTeacherDto());
  }

}
