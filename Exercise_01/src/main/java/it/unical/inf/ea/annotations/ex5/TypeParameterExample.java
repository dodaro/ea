package it.unical.inf.ea.annotations.ex5;

import java.util.Date;

public class TypeParameterExample<@TestTP T> {

  public void metodo(@TestTP T t) {
    System.out.println(t);
  }
}
