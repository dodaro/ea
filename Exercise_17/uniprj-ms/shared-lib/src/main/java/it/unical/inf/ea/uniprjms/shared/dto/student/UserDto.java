package it.unical.inf.ea.uniprjms.shared.dto.student;

import lombok.Data;

@Data
public class UserDto {

  private Long id;

  private String email;

  private String roles; //separati da ,

}
