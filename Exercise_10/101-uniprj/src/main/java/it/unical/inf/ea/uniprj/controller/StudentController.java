package it.unical.inf.ea.uniprj.controller;

import it.unical.inf.ea.uniprj.data.dto.Gender;
import it.unical.inf.ea.uniprj.data.dto.StudentBasicDto;
import it.unical.inf.ea.uniprj.data.dto.StudentDto;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @GetMapping("/students")
  public ResponseEntity<List<StudentDto>> all() {
    return ResponseEntity.ok(studentService.getAllSorted());
  }

  @GetMapping("/students/test")
  public ResponseEntity<List<StudentBasicDto>> test(@RequestParam("name") String name) {
    return ResponseEntity.ok(studentService.getByLastname(name));
  }

  @GetMapping("/students/{roles}")
  public ResponseEntity<List<StudentBasicDto>> all(@PathVariable("roles") String gender) {
    List<StudentBasicDto> employees = studentService.getByGender(Gender.valueOf(gender));
    return ResponseEntity.ok(employees);
  }

  @PostMapping("/students")
  public ResponseEntity<StudentBasicDto> add(@RequestBody StudentDto student) {
    return ResponseEntity.ok(studentService.save(student));
  }

  @PutMapping("/students/{id}")
  public ResponseEntity<StudentBasicDto> update(@PathVariable Long id,
                                            @RequestBody StudentDto employee) {
    StudentBasicDto s = studentService.updateStudent(id, employee);
    return ResponseEntity.ok(s);
  }

  @DeleteMapping("/students/{id}")
  public HttpStatus delete(@PathVariable Long id) {
    studentService.delete(id);
    return HttpStatus.OK;
  }
}
