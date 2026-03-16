package it.unical.inf.ea.lombok.ex6;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode
public class EqualsHashCodeDemo2 {
  
  private Long id;
  
  private static int defaultRole = 1;
  
  private LocalDate dob;
  
  private transient String dobString;
}