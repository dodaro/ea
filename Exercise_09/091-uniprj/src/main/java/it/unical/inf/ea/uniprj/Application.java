package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.config.CacheConfig;
import it.unical.inf.ea.uniprj.data.entities.Address;
import it.unical.inf.ea.uniprj.data.entities.Lesson;
import it.unical.inf.ea.uniprj.data.entities.Student;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.LessonService;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@Slf4j
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

    log.info(String.format("Created by: %s - Creation Date: %s", student.getCreatedBy(), student.getCreatedDate()));

    CacheManager cacheManager = context.getBean(CacheManager.class);
    ConcurrentHashMap map = (ConcurrentHashMap)cacheManager.getCache(CacheConfig.CACHE_FOR_STUDENTS).getNativeCache();

    LessonService lessonService = context.getBean(LessonService.class);
    Lesson lesson = new Lesson();
    lesson.setTitle("EA");
    lessonService.save(lesson);

  }

}
