package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.config.CacheConfig;
import it.unical.inf.ea.uniprj.data.entities.Student;
import it.unical.inf.ea.uniprj.dto.Gender;
import it.unical.inf.ea.uniprj.dto.StudentBasicDto;
import it.unical.inf.ea.uniprj.dto.StudentDto;
import it.unical.inf.ea.uniprj.dto.StudentValue;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface StudentService {

  void save(Student student);

  StudentBasicDto save(StudentDto student);

  Collection<Student> findAll(Specification<Student> spec);

  int count(Gender gender);

  List<StudentBasicDto> get(LocalDate date, String t);

  StudentBasicDto getById(Long id);

  List<StudentBasicDto> getAllByBirthDate(LocalDate from, LocalDate to);

  List<StudentBasicDto> getByLastname(String lastname);

  List<StudentBasicDto> getByLastNameAndFirstName(String lastname, String firstname);

  List<StudentValue> countByGender();

  List<StudentBasicDto> getByGender(Gender gender);

  List<StudentBasicDto> getByName(String name);

  @Cacheable(value = CacheConfig.CACHE_FOR_STUDENTS, key = "#root.methodName")
  Collection<StudentDto> findAll();

  void delete(Long id);

  //091
  List<StudentDto> getAllSorted();

  List<StudentDto> getAllSorted2();

  List<StudentDto> getAllSorted3(String colSort1, String colOrd1, String colSort2, String colOrd2);

  Page<StudentDto> getAllPaged(int page);

  Page<StudentDto> getAllByLastNameStartWith(String lastName, int page);

  StudentBasicDto updateStudent(Long id, StudentDto employee);
}
