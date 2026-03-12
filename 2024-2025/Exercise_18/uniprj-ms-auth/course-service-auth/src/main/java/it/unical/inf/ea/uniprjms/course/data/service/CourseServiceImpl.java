package it.unical.inf.ea.uniprjms.course.data.service;

import it.unical.inf.ea.uniprjms.course.data.entities.Course;
import it.unical.inf.ea.uniprjms.course.data.dao.CourseDao;
import it.unical.inf.ea.uniprjms.course.service.CourseEventProducer;
import it.unical.inf.ea.uniprjms.shared.dto.course.CourseTeacherDto;
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

  private final CourseEventProducer courseEventProducer;

  private final ModelMapper modelMapper;

  @Override
  public Course getById(Long id) {
    return courseDao.findById(id).get();
  }

  @Override
  public Course save(Course course) {
    Course saved = courseDao.save(course);
    // Pubblica l'evento di creazione corso
    courseEventProducer.publishCourseCreatedEvent(saved);
    return saved;
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

    if(true) //FIXME
      throw new NullPointerException();
    List<Course> courses = courseDao.findAll();
    return courses.stream().map(s ->
        modelMapper.map(s, CourseTeacherDto.class)).collect(Collectors.toList());
  }

}







