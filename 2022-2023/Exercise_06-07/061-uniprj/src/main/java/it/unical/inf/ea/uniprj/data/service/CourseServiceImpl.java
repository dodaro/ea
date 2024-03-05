package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.data.dao.CourseDao;
import it.unical.inf.ea.uniprj.data.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseDao courseDao;

  @Override
  public Course getById(Long id) {
    return courseDao.findById(id).get();
  }

  @Override
  public Course save(Course course) {
    return courseDao.save(course);
  }

  @Override
  public Optional<Course> getByTitle(String title) {
    return courseDao.findByTitle(title);
  }

  @Override
  public String getTitleById(long id) {
    return courseDao.findTitleById(id);
  }

  @Override
  public Collection<Course> getAll(Specification<Course> spec) {
    return courseDao.findAll(spec);
  }

  @Override
  public Collection<Course> getAll() {
    return courseDao.findAll();
  }

}
