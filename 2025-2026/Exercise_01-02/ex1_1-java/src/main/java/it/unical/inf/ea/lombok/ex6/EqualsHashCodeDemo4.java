package it.unical.inf.ea.lombok.ex6;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = {"name", "email"})
public class EqualsHashCodeDemo4 {
  
  private Long id;
  
  private String name;
  
  private String email;
}