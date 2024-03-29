package it.unical.inf.ea.uniprjms.ms.course.service;

import it.unical.inf.ea.uniprjms.ms.course.client.TeacherClient;
import it.unical.inf.ea.uniprjms.ms.course.data.service.CourseService;
import it.unical.inf.ea.uniprjms.ms.course.data.entities.Course;
import it.unical.inf.ea.uniprjms.ms.course.dto.CourseTeacherDto;
import it.unical.inf.ea.uniprjms.ms.course.dto.TeacherBasicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseTeacherService {

  private final CourseService courseService;

  private final TeacherClient teacherClient;

  public List<CourseTeacherDto> getCourseTeacherDto() {

    Collection<Course> courses = courseService.getAll();

    return courses.stream().map(c -> {
          TeacherBasicDto t = teacherClient.findTeacherById(c.getTeacherId());
          CourseTeacherDto dto = new CourseTeacherDto();
          dto.setCourseId(c.getId());
          dto.setCourseTitle(c.getTitle());
          dto.setTeacherId(t.getId());
          dto.setTeacherLastName(t.getFullName());
          return dto;
        }
      ).collect(Collectors.toList());
  }

}
