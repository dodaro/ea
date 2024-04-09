package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.data.dao.TeacherDao;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import it.unical.inf.ea.uniprj.data.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest extends AbstractUniPrjTest {

  @Autowired
  private TeacherDao teacherDao;

  @Autowired
  private TeacherService teacherService;

  @Test
  public void test1() {
    Teacher teacher = new Teacher();
    teacher.setFirstName("Angelo");
    teacher.setLastName("Fucsia");

    teacherDao.save(teacher);

    System.out.println(teacherDao.findAll().size() +"-"+ courseService.getAll().size());
    try {
      teacherService.deleteTeacherAndEmptyCourse();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(teacherDao.findAll().size() +"-"+ courseService.getAll().size());
  }

  @Test
  public void transactionalTest()
  {
//    teacherService.deleteTeacherAndCourseEmpty();
  }

}
