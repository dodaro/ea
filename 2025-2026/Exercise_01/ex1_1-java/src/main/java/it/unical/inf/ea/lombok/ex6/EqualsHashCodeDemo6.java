package it.unical.inf.ea.lombok.ex6;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EqualsHashCodeDemo6 {
  
  @EqualsAndHashCode.Include
  private Long id;
  
  private double salary;
  
  private String email;
  
}