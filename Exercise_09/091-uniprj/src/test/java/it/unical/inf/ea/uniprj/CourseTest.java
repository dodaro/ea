package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.data.dao.UniSpecification;
import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest extends AbstractUniPrjTest {

  @Test
  public void test() {

    Optional<Course> opt = courseService.getByTitle("ASD");

    if(opt.isEmpty()) {
      System.out.println("NOT FOUNDED");
    }
    else {
      System.out.println(opt.get().getId());
    }
    System.out.println("---");
    String title = courseService.getTitleById(6L);
    System.out.println(title);
    System.out.println("---");

    courseService.getAll(UniSpecification.anotherFilter("Bianchi", "Gialli")).stream().forEach(System.out::println);

  }

  @Test //switch students property in Course from Eager ti Lazy and viceversa
  public void testStudentEagerVsLazy() {
    List<Student> students = courseService.getByTitle("Lettere Moderne").get().getStudents();
    System.out.println(students.size());
    System.out.println(students.get(0).getId());
  }

}
