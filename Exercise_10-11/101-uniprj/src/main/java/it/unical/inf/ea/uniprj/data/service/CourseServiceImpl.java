package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.data.dao.CourseDao;
import it.unical.inf.ea.uniprj.data.dto.CourseTeacherDto;
import it.unical.inf.ea.uniprj.data.entities.Course;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseDao courseDao;


  private final ModelMapper modelMapper;

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

  @Override
  public List<CourseTeacherDto> getCourseTeacherDto() {

    List<Course> courses = courseDao.findAll();
    return courses.stream().map(s ->
        modelMapper.map(s, CourseTeacherDto.class)).collect(Collectors.toList());
  }

}







