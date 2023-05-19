package it.unical.inf.ea.uniprjms.ms.course.dto;

import lombok.Data;

@Data
public class CourseTeacherDto {

  private long courseId;

  private long teacherId;

  private String courseTitle;

  private String teacherLastName;
}
