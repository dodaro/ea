package it.unical.inf.ea.uniprj.controller;

import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import it.unical.inf.ea.uniprj.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher-api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    public final TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> all() {
        return ResponseEntity.ok(teacherService.getAllTeacher());
    }

}
