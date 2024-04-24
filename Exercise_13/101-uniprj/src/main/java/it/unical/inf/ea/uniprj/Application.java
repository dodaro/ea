package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.core.services.ThesisService;
import it.unical.inf.ea.uniprj.dto.Gender;
import it.unical.inf.ea.uniprj.dto.StudentBasicDto;
import it.unical.inf.ea.uniprj.dto.StudentDto;
import it.unical.inf.ea.uniprj.dto.TeacherBasicDto;
import it.unical.inf.ea.uniprj.dto.Thesis;
import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.CourseService;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

    long studentId = studentTest(context);
    long teacherId = teacherTest(context);

    //    courseServiceTest(context);

    ThesisService thesisService = context.getBean(ThesisService.class);
    Thesis thesis = thesisService.generateThesis("la mia tesi", studentId);
    System.out.println(thesis);
  }

  private static void courseServiceTest(ConfigurableApplicationContext context) {
    CourseService courseService = context.getBean(CourseService.class);
    TeacherService teacherService = context.getBean(TeacherService.class);
    Teacher teacher = new Teacher();
    teacher.setFirstName("Sig");
    teacher.setLastName("Rottermeier");
    teacher = teacherService.save(teacher);

    Course course = new Course();
    course.setTitle("EA");
    course.setTeacher(teacher);
    courseService.save(course);

    courseService.getCourseTeacherDto().forEach(System.out::println);
  }

  private static long teacherTest(ConfigurableApplicationContext context) {
    TeacherService teacherService = context.getBean(TeacherService.class);
    Teacher teacher = new Teacher();
    teacher.setFirstName("Mary");
    teacher.setLastName("Poppins");
    teacher = teacherService.save(teacher);

    TeacherBasicDto teacherBasicDto = teacherService.getTeacherBasicDtoById(1l);
    System.out.println(teacherBasicDto);
    return teacherBasicDto.getId();
  }

  private static long studentTest(ConfigurableApplicationContext context) {
    StudentService studentService = context.getBean(StudentService.class);
    //		teacherService.add(new Teacher(....));
    StudentDto s = new StudentDto();
    s.setFirstName("ciccio");
    s.setGender(Gender.MALE);
    s.setLastName("pasticcio");
    s.setAddressCity("a");
    s.setAddressStreet("b");
    s.setAddressNumber("c");
    s.setBirthDate(LocalDate.now());
    s.setWantsNewsletter(false);
    StudentBasicDto student = studentService.save(s);
    System.out.println(student);

    studentService.findAll().stream().forEach(System.out::println);
    return student.getId();
  }

}
