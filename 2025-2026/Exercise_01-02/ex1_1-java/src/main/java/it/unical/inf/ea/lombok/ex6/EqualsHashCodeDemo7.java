package it.unical.inf.ea.lombok.ex6;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"id", "email"})
public class EqualsHashCodeDemo7 {
  
  private Long id;
  
  private double salary;
  
  private String email;
  
}