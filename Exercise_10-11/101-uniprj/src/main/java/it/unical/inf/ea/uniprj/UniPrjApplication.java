package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.core.services.ThesisService;
import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.CourseService;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import it.unical.inf.ea.uniprj.dto.Gender;
import it.unical.inf.ea.uniprj.dto.StudentBasicDto;
import it.unical.inf.ea.uniprj.dto.StudentDto;
import it.unical.inf.ea.uniprj.dto.TeacherBasicDto;
import it.unical.inf.ea.uniprj.dto.Thesis;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class UniPrjApplication {

  public static void main(String[] args) {
    SpringApplication.run(UniPrjApplication.class, args);
  }
}
