package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.data.dao.UniSpecification;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherTest extends AbstractUniPrjTest {

  @Test
  public void test() {


    List<Teacher> listA = teacherService.getAllTeacherByLastFilter();

    List<Teacher> list = teacherService.getAllTeacher(UniSpecification.theLastFilter("Aaa"));
    Assert.assertEquals(4, list.size());
//    list.stream().forEach(System.out::println);

  }

}
