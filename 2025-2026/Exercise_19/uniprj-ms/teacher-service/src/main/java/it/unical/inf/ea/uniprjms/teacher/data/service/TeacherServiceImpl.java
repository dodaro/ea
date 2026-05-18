package it.unical.inf.ea.uniprjms.teacher.data.service;

import it.unical.inf.ea.uniprjms.shared.config.CacheConfig;
import it.unical.inf.ea.uniprjms.teacher.data.dao.TeacherDao;
import it.unical.inf.ea.uniprjms.teacher.data.entities.Teacher;
import it.unical.inf.ea.uniprjms.shared.dto.TeacherBasicDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    Teacher teacher = teacherDao.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(String.format("Don't exist a teacher with id: [%s]", id)));
    return toDto(teacher);
  }

  @Override
  public List<TeacherBasicDto> getAllTeacherDtos() {
    return teacherDao.findAll().stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  private TeacherBasicDto toDto(Teacher teacher) {
    TeacherBasicDto dto = modelMapper.map(teacher, TeacherBasicDto.class);
    dto.setFullName(teacher.getFirstName() + " " + teacher.getLastName());
    return dto;
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
