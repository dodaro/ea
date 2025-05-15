package it.unical.inf.ea.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Country {
  private String name;
  private long population;

}