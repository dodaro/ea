package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.data.entities.Address;
import it.unical.inf.ea.uniprj.data.entities.Student;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    StudentService studentService = context.getBean(StudentService.class);
    //		teacherService.add(new Teacher(....));
    Student s = new Student();
    s.setFirstName("ciccio");
    s.setGender(Student.Gender.MALE);
    s.setLastName("pasticcio");
    s.setAddress(new Address("a","b","c"));
    s.setBirthDate(LocalDate.now());
    s.setWantsNewsletter(false);
    Student student = studentService.save(s);
    System.out.println(student.getCreatedDate());
  }

}
