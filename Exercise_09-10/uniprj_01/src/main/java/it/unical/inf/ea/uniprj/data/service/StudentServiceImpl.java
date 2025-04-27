package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.data.dao.StudentDao;
import it.unical.inf.ea.uniprj.data.dao.UniSpecification;
import it.unical.inf.ea.uniprj.data.dto.StudentValue;
import it.unical.inf.ea.uniprj.data.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentDao studentDao;

  @Override
  public Student save(Student student) {
    return studentDao.save(student);
  }

  @Override
  public Collection<Student> findAll(Specification<Student> spec) {
    return studentDao.findAll(spec);
  }

  @Override
  public int count(Student.Gender gender) {
    return studentDao.countByGender(gender);
  }

  @Override
  public List<Student> get(LocalDate birthdate, String lastNameStart) {
    return studentDao.findAllByBirthDateLessThanEqualAndFirstNameIsNotNullAndLastNameStartingWithAndWantsNewsletterTrueOrderByGenderAsc(birthdate, lastNameStart);
  }

  @Override
  public List<Student> getAllByBirthDate(LocalDate from, LocalDate to) {
    return studentDao.findAllByBirthDateBetweenOrderByLastNameDesc(from, to);
  }

  @Override
  public List<Student> getByLastname(String lastname) {
    return studentDao.ciccio(lastname);
  }

  @Override
  public List<Student> getByLastNameAndFirstName(String lastname,
      String firstname) {
    return studentDao.findAllByLastNameAndFirstName(lastname, firstname);
  }

  @Override
  public List<StudentValue> countByGender() {
    return studentDao.countByGenger();
  }

  @Override
  public List<Student> getByGender(Student.Gender gender) {
    Student s = new Student();
    s.setGender(gender);
    Example<Student> ex = Example.of(s);
    List<Student> all = studentDao.findAll(ex);
    return all;
  }

  @Override
  public List<Student> getByName(String name) {
    Student s = new Student();
    s.setFirstName(name);
    Example<Student> ex = Example.of(s, ExampleMatcher.matchingAll().withIgnoreCase());
    List<Student> all = studentDao.findAll(ex);
    return all;
  }

  @Override
  public List<Student> filter(UniSpecification.Filter f) {
    return studentDao.findAll(UniSpecification.withFilter(f));
  }
}
