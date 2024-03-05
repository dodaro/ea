package it.unical.inf.ea.lombok.ex6;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EqualsHashCodeDemo3 {
  
  private Long id;
  
  @EqualsAndHashCode.Exclude
  private String name;
  
  @EqualsAndHashCode.Exclude
  private String email;
}