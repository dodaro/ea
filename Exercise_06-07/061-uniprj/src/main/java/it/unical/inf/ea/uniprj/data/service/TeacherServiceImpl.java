package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.data.dao.CourseDao;
import it.unical.inf.ea.uniprj.data.dao.TeacherDao;
import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.Teacher;
import jakarta.persistence.Version;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherDao teacherDao;

  @Autowired
  private CourseDao courseDao;

  @Override
  public List<Teacher> getAllTeacher() {
    return teacherDao.findAll();
  }

  @Transactional
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
  @Version
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

  private void methodWithException() {
    throw new RuntimeException("MOCK ERROR");
  }
}
