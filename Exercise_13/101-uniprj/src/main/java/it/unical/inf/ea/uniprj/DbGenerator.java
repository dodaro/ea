package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.data.entities.Address;
import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.CourseMaterial;
import it.unical.inf.ea.uniprj.data.entities.Student;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.CourseMaterialService;
import it.unical.inf.ea.uniprj.data.service.CourseService;
import it.unical.inf.ea.uniprj.data.service.StudentService;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import it.unical.inf.ea.uniprj.dto.Gender;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DbGenerator implements ApplicationRunner {

  @Value("classpath:data/students.csv")
  private Resource studentsRes;

  @Value("classpath:data/teachers.csv")
  private Resource teachersRes;

  @Value("classpath:data/courses.csv")
  private Resource coursesRes;

  @Autowired
  protected TeacherService teacherService;

  @Autowired
  protected StudentService studentService;

  @Autowired
  protected CourseService courseService;

  @Autowired
  protected CourseMaterialService courseMaterialService;


  public void createDb() {

    try {
      CSVParser teachersCsv = CSVFormat.DEFAULT.withDelimiter(';')
          .parse(new InputStreamReader(teachersRes.getInputStream()));
      for (CSVRecord record : teachersCsv) {
        insertTeacher(record.get(0), record.get(1));
      }

      CSVParser coursesCsv = CSVFormat.DEFAULT.withDelimiter(';')
          .parse(new InputStreamReader(coursesRes.getInputStream()));
      for (CSVRecord record : coursesCsv) {
        insertCourse(record.get(0), record.get(1), record.get(2), record.get(3));
      }

      CSVParser studentsCsv = CSVFormat.DEFAULT.withDelimiter(';')
          .parse(new InputStreamReader(studentsRes.getInputStream()));
      for (CSVRecord record : studentsCsv) {
        insertStudent(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5),
            record.get(6), record.get(7), record.get(8));
      }
    } catch (IOException e) {
      throw new RuntimeException("ERROR TO GENERATE DB TEST");
    }
  }

  private void insertStudent(String firstName,
      String lastName,
      String birthDate,
      String gender,
      String wantsNewsletter,
      String streetAddress,
      String numberAddress,
      String cityAddress,
      String courses) {
    Student student = new Student();
    student.setFirstName(firstName);
    student.setLastName(lastName);

    student.setBirthDate(LocalDate.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE));
    student.setGender(Gender.valueOf(gender));
    student.setWantsNewsletter(Boolean.valueOf(wantsNewsletter));
    student.setAddress(new Address(streetAddress, numberAddress, cityAddress));

    String[] coursesArray = courses.split("-");
    for (String courseId : coursesArray) {
      Course course = courseService.getById(Long.parseLong(courseId));
      student.addCourse(course);
    }

    studentService.save(student);
  }

  private void insertTeacher(String firstName,
      String lastName) {
    Teacher teacher = new Teacher();
    teacher.setFirstName(firstName);
    teacher.setLastName(lastName);

    teacherService.save(teacher);
  }

  private void insertCourse(String title,
      String idTeacher,
      String idCourseMaterial,
      String urlMaterial) {
    Teacher teacher = teacherService.getTeacherById(Long.valueOf(idTeacher));
    Course course = new Course();
    course.setTeacher(teacher);
    course.setTitle(title);
    courseService.save(course);

    CourseMaterial courseMaterial = new CourseMaterial();
    courseMaterial.setId(Long.parseLong(idCourseMaterial));
    courseMaterial.setCourse(course);
    courseMaterial.setUrl(urlMaterial);
    courseMaterialService.save(courseMaterial);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    createDb();
  }
}