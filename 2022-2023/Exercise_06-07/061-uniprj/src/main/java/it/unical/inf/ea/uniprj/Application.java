package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {

//    SpringApplication.run(Application.class, args);

    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    TeacherService teacherService = context.getBean(TeacherService.class);
    //		teacherService.add(new Teacher(....));
    List<Teacher> allTeacher = teacherService.getAllTeacher();
  }

}
