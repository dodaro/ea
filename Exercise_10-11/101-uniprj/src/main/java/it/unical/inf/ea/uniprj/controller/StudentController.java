package it.unical.inf.ea.uniprj.controller;

import io.swagger.v3.oas.annotations.Parameter;
import it.unical.inf.ea.uniprj.core.services.ThesisService;
import it.unical.inf.ea.uniprj.dto.Gender;
import it.unical.inf.ea.uniprj.dto.StudentBasicDto;
import it.unical.inf.ea.uniprj.dto.StudentDto;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import it.unical.inf.ea.uniprj.dto.Thesis;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/student-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  private final ThesisService thesisService;

  @GetMapping("/students")
  public ResponseEntity<List<StudentDto>> all() {
    return ResponseEntity.ok(studentService.getAllSorted());
  }

  @GetMapping("/students/{idStudent}")
  public ResponseEntity<StudentBasicDto> getById(@PathVariable("idStudent") Long id) {
    StudentBasicDto s = studentService.getById(id);
    if(s==null)
      return ResponseEntity.notFound().build(); // meglio farlo nel service e gestire l'eccezione con l'handler
    return ResponseEntity.ok(s);
  }

  @GetMapping("/students/test")
  public ResponseEntity<List<StudentBasicDto>> test(@RequestParam("name") String name) {
    return ResponseEntity.ok(studentService.getByLastname(name));
  }

  @GetMapping("/students/roles/{role}")
  public ResponseEntity<List<StudentBasicDto>> all(@PathVariable("role") String gender) {
    List<StudentBasicDto> employees = studentService.getByGender(Gender.valueOf(gender));
    return ResponseEntity.ok(employees);
  }

  @PostMapping("/students")
  public ResponseEntity<StudentBasicDto> add(@RequestBody @Valid StudentDto student) {
    return ResponseEntity.ok(studentService.save(student));
  }

  @PutMapping("/students/{idStudent}")
  public ResponseEntity<StudentBasicDto> update(@PathVariable("idStudent") Long id, @RequestBody StudentDto employee) {
    StudentBasicDto s = studentService.updateStudent(id, employee);
    return ResponseEntity.ok(s);
  }

  @DeleteMapping("/students/{idStudent}")
  public HttpStatus delete(@PathVariable("idStudent") Long id) {
    studentService.delete(id);
    return HttpStatus.OK;
  }

  @PostMapping("/thesis/{idStudent}")
  public ResponseEntity<Thesis> add(@PathVariable("idStudent") Long id, @RequestParam String title) {
    return ResponseEntity.ok(thesisService.generateThesis(title, id));
  }
}
