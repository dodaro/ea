package it.unical.inf.ea.uniprjms.ms.student.controller;

import it.unical.inf.ea.uniprjms.ms.student.config.JwtUtil;
import it.unical.inf.ea.uniprjms.ms.student.config.LoggedUserToken;
import it.unical.inf.ea.uniprjms.ms.student.data.service.StudentService;
import it.unical.inf.ea.uniprjms.ms.student.dto.Gender;
import it.unical.inf.ea.uniprjms.ms.student.dto.StudentBasicDto;
import it.unical.inf.ea.uniprjms.ms.student.dto.StudentDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
//@RequestMapping("/student-api/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/students")
  public ResponseEntity<List<StudentDto>> all(HttpServletRequest request) {
    printLoggedUserQuietly(request);
    return ResponseEntity.ok(studentService.getAllSorted());
  }

  private static void printLoggedUserQuietly(HttpServletRequest request) {
    try {
      LoggedUserToken loggedUserToken = JwtUtil.extractToken(request);
      log.info(loggedUserToken.getUsername());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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
}
