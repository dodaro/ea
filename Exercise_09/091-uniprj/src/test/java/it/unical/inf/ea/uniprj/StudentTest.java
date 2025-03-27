package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.data.dao.UniSpecification;
import it.unical.inf.ea.uniprj.data.dto.StudentValue;
import it.unical.inf.ea.uniprj.data.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest extends AbstractUniPrjTest {

  @Test
  public void test() {

    //LocalDate.of(1999, Month.AUGUST,10)
    List<Student> list = studentService.getByLastNameAndFirstName("Fff", "Eee");
    System.out.println(list.size());
    list.stream().forEach(System.out::println);


    System.out.println("---");
    list = studentService.getByLastname("Bbb");
    System.out.println(list.size());
    list.stream().forEach(System.out::println);

    System.out.println("---");
    list = studentService.getAllByBirthDate(LocalDate.of(2000, Month.JANUARY, 1),
        LocalDate.of(2000, Month.DECEMBER, 31));
    list.stream().forEach(System.out::println);

    System.out.println("---");
    list =
        studentService.get(LocalDate.of(2020, Month.JANUARY, 1), "T");
    list.stream().forEach(System.out::println);

    int count = studentService.count(Student.Gender.MALE);
    System.out.println(count);

    System.out.println("---");
    UniSpecification.Filter filter = new UniSpecification.Filter();
    filter.setAge(20);
    studentService.findAll(UniSpecification.withFilter(filter)).stream().forEach(System.out::println);
  }

  @Test
  public void testCount() {
    List<StudentValue> res = studentService.countByGender();
    for (StudentValue sv: res) {
      System.out.println(sv.getGender() + " - " + sv.getCount());
    }
  }
}
