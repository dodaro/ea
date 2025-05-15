package it.unical.inf.ea.uniexample;

import it.unical.inf.ea.uniexample.dao.StudentDao;
import it.unical.inf.ea.uniexample.entities.Address;
import it.unical.inf.ea.uniexample.entities.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTest {
    
    @Autowired
    private StudentDao studentDao;
    

    @Test
    public void whenFindingAllCustomers_thenCorrect() {
        Student johnDoe = new Student();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        johnDoe.setBirthDateAsLocalDate(LocalDate.of(2000, Month.JANUARY, 18));
        johnDoe.setGender(Student.Gender.MALE);
        johnDoe.setWantsNewsletter(true);
        johnDoe.setAddress(new Address("Baker Street", "221B", "London"));
        studentDao.save(johnDoe);
    }
}