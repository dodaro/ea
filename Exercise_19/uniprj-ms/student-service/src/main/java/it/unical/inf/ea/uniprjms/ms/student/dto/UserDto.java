package it.unical.inf.ea.uniprjms.ms.student.dto;

import lombok.Data;

@Data
public class UserDto {

  private Long id;

  private String email;

  private String roles; //separati da ,

}
