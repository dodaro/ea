package it.unical.inf.ea.uniprj.dto;

import lombok.Data;

@Data
public class Thesis {

  private Long teacherId;

  private Long studentId;

  private String teacherFirstName;

  private String teacherLastName;

  private String studentFirstName;

  private String studentLastName;

  private String title;

}
