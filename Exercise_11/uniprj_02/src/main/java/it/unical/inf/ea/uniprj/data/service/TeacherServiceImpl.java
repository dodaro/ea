package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.config.CacheConfig;
import it.unical.inf.ea.uniprj.data.dao.CourseDao;
import it.unical.inf.ea.uniprj.data.dao.TeacherDao;
import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

  private final TeacherDao teacherDao;

  private final CourseDao courseDao;

  @Override
  public List<Teacher> getAllTeacher() {
    return teacherDao.findAll();
  }

  /*
    jakarta.transaction.Transactional non ha readonly
    usare: org.springframework.transaction.annotation.Transactional
   */
  @Transactional(readOnly = true)
  @Override
  public void deleteTeacherAndEmptyCourse() {
    List<Course> all = courseDao.findAll();
    for (Course c : all) {
      if (c.getStudents().isEmpty()) {
        Teacher teacher = c.getTeacher();
        courseDao.delete(c);
        teacherDao.delete(teacher);
        methodWithException(); //MOCK LINE
      }
    }

  }

  @Override
  public void add(Teacher teacher) {
    teacherDao.save(teacher);
  }

  @Override
  public Teacher save(Teacher teacher) {
    return teacherDao.save(teacher);
  }

  @Override
  @Cacheable(value = CacheConfig.CACHE_FOR_TEACHER_ID, key = "#id")
  public Teacher getById(Long id) {
    Optional<Teacher> opt = teacherDao.findById(id);
    return opt.orElseThrow(()->new RuntimeException(String.format("Don't exist a teacher with id: [%s]", id)));
  }

  @Override
  public List<Teacher> getAllTeacherByLastFilter(String... names) {
    Specification<Teacher> spec = teacherDao.theLastFilter(names);
    return teacherDao.findAll(spec);
  }

  @Override
  public List<Teacher> getAllTeacher(Specification<Teacher> spec) {
    return teacherDao.findAll(spec);
  }

  @Override
  public List<Teacher> getAllTeacherPaged(Specification<Teacher> spec) {
    return teacherDao.findAll(spec, PageRequest.of(1, 5)).stream().toList();
  }

  private void methodWithException() {
    throw new RuntimeException("MOCK ERROR");
  }
}
