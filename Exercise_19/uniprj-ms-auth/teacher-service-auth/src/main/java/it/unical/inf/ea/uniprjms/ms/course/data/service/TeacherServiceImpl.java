package it.unical.inf.ea.uniprjms.ms.course.data.service;

import it.unical.inf.ea.uniprjms.ms.course.config.CacheConfig;
import it.unical.inf.ea.uniprjms.ms.course.data.dao.TeacherDao;
import it.unical.inf.ea.uniprjms.ms.course.data.entities.Teacher;
import it.unical.inf.ea.uniprjms.ms.course.dto.TeacherBasicDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

  private final TeacherDao teacherDao;

  private final ModelMapper modelMapper;

  @Override
  public List<Teacher> getAllTeacher() {
    return teacherDao.findAll();
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
  public TeacherBasicDto getTeacherBasicDtoById(Long id) {
    Optional<Teacher> opt = teacherDao.findById(id);
    Teacher teacher = opt.orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist a teacher with id: [%s]", id)));

    return modelMapper.map(teacher, TeacherBasicDto.class);
  }

  @Override
  public Teacher getTeacherById(Long id) {
    return teacherDao.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist a teacher with id: [%s]", id)));
  }

  @Override
  public List<Teacher> getAllTeacher(Specification<Teacher> spec) {
    return teacherDao.findAll(spec);
  }

  private void methodWithException() {
    throw new RuntimeException("MOCK ERROR");
  }
}
