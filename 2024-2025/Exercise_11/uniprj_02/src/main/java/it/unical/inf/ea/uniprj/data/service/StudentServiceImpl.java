package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.config.CacheConfig;
import it.unical.inf.ea.uniprj.data.dao.StudentDao;
import it.unical.inf.ea.uniprj.data.dto.StudentValue;
import it.unical.inf.ea.uniprj.data.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentDao studentDao;

  @Override
  public Student save(Student student) {
    return studentDao.save(student);
  }

  @Override
  public Collection<Student> findAll(Specification<Student> spec) {
    return studentDao.findAll(spec);
  }

  @Override
  @CacheEvict(allEntries = true, value = { CacheConfig.CACHE_FOR_STUDENTS }) // invalido la cache perché c'è stata un'eliminazione
  public void delete(Student student) {
    studentDao.delete(student);
  }

  @Override
  @Cacheable(value = CacheConfig.CACHE_FOR_STUDENTS, key = "#root.methodName")
  public Collection<Student> findAll() {
    return studentDao.findAll();
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
  @Cacheable(value = CacheConfig.CACHE_FOR_STUDENTS, key = "#lastname + '-' + #firstname")
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

  //091

  private final static int SIZE_FOR_PAGE = 20;

  @Override
  public List<Student> getAllSorted() {
    return studentDao.findAll( Sort.by("lastName").ascending());
  }

  @Override
  public List<Student> getAllSorted2() {
    return studentDao.findAll( Sort.by("lastName").ascending()
        .and(Sort.by("firstName").descending()));
  }

  @Override
  public List<Student> getAllSorted3(String colSort1, String colOrd1, String colSort2, String colOrd2) {
    List<Sort.Order> orders = new ArrayList<>();

    orders.add(new Sort.Order(Sort.Direction.fromString(colOrd1), colSort1));
    orders.add(new Sort.Order(Sort.Direction.fromString(colOrd2), colSort2));

    return studentDao.findAll( Sort.by(orders));
  }

  @Override
  public Page<Student> getAllPaged(int page) {
    return studentDao.findAll( PageRequest.of(0, 5));
  }

  @Override
  public Page<Student> getAllByLastNameStartWith(String lastName, int page) {

    PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE, Sort.by("lastName").ascending());
    return studentDao.findAllByLastNameLike(lastName, pageRequest);
  }
}
