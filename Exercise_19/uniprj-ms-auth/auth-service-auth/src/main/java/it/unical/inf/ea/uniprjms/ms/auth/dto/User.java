package it.unical.inf.ea.uniprjms.ms.auth.dto;

import lombok.Data;

@Data
public class User {

  private Long id;

  private String email;

  private String password;

  private boolean deleted = false;

  private boolean active = false;

  private String roles; //separati da ,

}
