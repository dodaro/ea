package it.unical.inf.ea.uniprj.dto;

import lombok.Data;

@Data
public class CourseTeacherDto {

  private long courseId;

  private long teacherId;

  private String courseTitle;

  private String teacherLastName;
}
