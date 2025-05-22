package it.unical.inf.ea.uniprjms.student.controller;

import it.unical.inf.ea.uniprjms.student.data.service.StudentService;
import it.unical.inf.ea.uniprjms.shared.dto.student.Gender;
import it.unical.inf.ea.uniprjms.shared.dto.student.StudentBasicDto;
import it.unical.inf.ea.uniprjms.shared.dto.student.StudentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

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

  @GetMapping("/students/search")
  public ResponseEntity<List<StudentBasicDto>> getByLastname(@RequestParam("name") String name) {
    return ResponseEntity.ok(studentService.getByLastname(name));
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
}
