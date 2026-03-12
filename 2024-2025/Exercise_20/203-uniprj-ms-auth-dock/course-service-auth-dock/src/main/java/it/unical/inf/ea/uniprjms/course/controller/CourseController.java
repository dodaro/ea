package it.unical.inf.ea.uniprjms.course.controller;

import it.unical.inf.ea.uniprjms.course.data.entities.Course;
import it.unical.inf.ea.uniprjms.course.data.service.CourseService;
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

  private final CourseService courseService;

  @GetMapping("/courses-teachers")
  public ResponseEntity<List<CourseTeacherDto>> coursesTeachers() {
    return ResponseEntity.ok(courseTeacherService.getCourseTeacherDto());
  }

  @GetMapping("/courses")
  public ResponseEntity<List<Course>> all() {
    return ResponseEntity.ok(courseService.getAll().stream().toList());
  }

}
