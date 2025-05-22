package it.unical.inf.ea.uniprjms.student.data.service;

import it.unical.inf.ea.uniprjms.shared.config.CacheConfig;
import it.unical.inf.ea.uniprjms.student.data.dao.StudentDao;
import it.unical.inf.ea.uniprjms.student.data.entities.Address;
import it.unical.inf.ea.uniprjms.student.data.entities.Student;
import it.unical.inf.ea.uniprjms.shared.dto.student.Gender;
import it.unical.inf.ea.uniprjms.shared.dto.student.StudentBasicDto;
import it.unical.inf.ea.uniprjms.shared.dto.student.StudentDto;
import it.unical.inf.ea.uniprjms.shared.dto.student.StudentValue;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentDao studentDao;

  private final ModelMapper modelMapper;

  @Override
  public void save(Student student) {
    studentDao.save(student);
  }

  @Override
  public StudentBasicDto save(StudentDto studentDto) {
    Student student = modelMapper.map(studentDto, Student.class);
    Student s = studentDao.save(student);
    return modelMapper.map(s, StudentBasicDto.class);
  }

  @Override
  public Collection<Student> findAll(Specification<Student> spec) {
    return studentDao.findAll(spec);
  }

  @Override
  public int count(Gender gender) {
    return studentDao.countByGender(gender);
  }

  @Override
  public List<StudentBasicDto> get(LocalDate birthdate, String lastNameStart) {
    return studentDao.findAllByBirthDateLessThanEqualAndFirstNameIsNotNullAndLastNameStartingWithAndWantsNewsletterTrueOrderByGenderAsc(birthdate, lastNameStart)
        .stream().map(s -> modelMapper.map(s, StudentBasicDto.class)).collect(Collectors.toList());
  }

  @Override
  public StudentBasicDto getById(Long id) {
    Student student = studentDao.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist a teacher with id: [%s]", id)));
    return modelMapper.map(student, StudentBasicDto.class);
  }

  @Override
  public List<StudentBasicDto> getAllByBirthDate(LocalDate from, LocalDate to) {
    return studentDao.findAllByBirthDateBetweenOrderByLastNameDesc(from, to).stream().map(s -> modelMapper.map(s, StudentBasicDto.class)).collect(Collectors.toList());
  }

  @Override
  public List<StudentBasicDto> getByLastname(String lastname) {
    return studentDao.ciccio(lastname).stream().map(s -> modelMapper.map(s, StudentBasicDto.class)).collect(Collectors.toList());
  }

  @Override
  @Cacheable(value = CacheConfig.CACHE_FOR_STUDENTS, key = "#lastname + '-' + #firstname")
  public List<StudentBasicDto> getByLastNameAndFirstName(String lastname,
      String firstname) {
    return studentDao.findAllByLastNameAndFirstName(lastname, firstname)
        .stream().map(s -> modelMapper.map(s, StudentBasicDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<StudentValue> countByGender() {
    return studentDao.countByGenger();
  }

  @Override
  public List<StudentBasicDto> getByGender(Gender gender) {
    Student sx = new Student();
    sx.setGender(gender);
    Example<Student> ex = Example.of(sx);
    List<Student> all = studentDao.findAll(ex);
    return all.stream().map(s -> modelMapper.map(s, StudentBasicDto.class)).collect(Collectors.toList());
  }

  @Override
  public List<StudentBasicDto> getByName(String name) {
    Student example = new Student();
    example.setFirstName(name);
    Example<Student> ex = Example.of(example, ExampleMatcher.matchingAll().withIgnoreCase());
    List<Student> all = studentDao.findAll(ex);
    return all.stream().map(s -> modelMapper.map(s, StudentBasicDto.class)).collect(Collectors.toList());
  }

  //091

  private final static int SIZE_FOR_PAGE = 20;

  @Override
  @CacheEvict(allEntries = true, value = { CacheConfig.CACHE_FOR_STUDENTS })
  public void delete(Long id) {
    studentDao.deleteById(id);
  }

  @Override
  @Cacheable(value = CacheConfig.CACHE_FOR_STUDENTS, key = "#root.methodName")
  public Collection<StudentDto> findAll() {
    return studentDao.findAll().stream()
        .map(s -> modelMapper.map(s, StudentDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<StudentDto> getAllSorted() {
    return studentDao.findAll( Sort.by("lastName").ascending()).stream().map(s -> modelMapper.map(s, StudentDto.class)).collect(Collectors.toList());
  }

  @Override
  public List<StudentDto> getAllSorted2() {
    List<Student> students = studentDao.findAll(Sort.by("lastName").ascending().and(Sort.by("lastName").descending()));
    return students.stream().map(s -> modelMapper.map(s, StudentDto.class)).collect(Collectors.toList());
  }

  @Override
  public List<StudentDto> getAllSorted3(String colSort1, String colOrd1, String colSort2, String colOrd2) {
    List<Sort.Order> orders = new ArrayList<>();

    orders.add(new Sort.Order(Sort.Direction.fromString(colOrd1), colSort1));
    orders.add(new Sort.Order(Sort.Direction.fromString(colOrd2), colSort2));

    List<Student> students = studentDao.findAll(Sort.by(orders));
    return students.stream().map(s -> modelMapper.map(s, StudentDto.class)).collect(Collectors.toList());
  }

  @Override
  public Page<StudentDto> getAllPaged(int page) {
    Page<Student> students = studentDao.findAll(PageRequest.of(0, 5));
    List<StudentDto> collect = students.stream().map(s -> modelMapper.map(s, StudentDto.class)).collect(Collectors.toList());
    return new PageImpl<>(collect);
  }

  @Override
  public Page<StudentDto> getAllByLastNameStartWith(String lastName, int page) {
    PageRequest pageRequest = PageRequest.of(SIZE_FOR_PAGE, page, Sort.by("lastName").ascending());
    List<StudentDto> collect = studentDao.findAllByLastNameLike(lastName, pageRequest).stream().map(s -> modelMapper.map(s, StudentDto.class)).collect(Collectors.toList());
    return new PageImpl<>(collect);
  }

  @Override
  public StudentBasicDto updateStudent(Long id, StudentDto studentToSave) {
    return studentDao.findById(id).map(student -> {
      student.setLastName(studentToSave.getLastName());
      student.setFirstName(studentToSave.getFirstName());
      student.setWantsNewsletter(studentToSave.isWantsNewsletter());
      student.setAddress(new Address(studentToSave.getAddressStreet(), studentToSave.getAddressNumber(), studentToSave.getAddressCity()));
      student.setBirthDate(studentToSave.getBirthDate());
      student.setGender(studentToSave.getGender());
      return modelMapper.map(studentDao.save(student), StudentBasicDto.class);
    }).orElseThrow(() -> new EntityNotFoundException());
  }
}

