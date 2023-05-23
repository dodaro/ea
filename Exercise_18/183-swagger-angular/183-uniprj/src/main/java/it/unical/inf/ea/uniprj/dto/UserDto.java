package it.unical.inf.ea.uniprj.dto;

import lombok.Data;

@Data
public class UserDto {

  private Long id;

  private String email;

  private String roles; //separati da ,

}
