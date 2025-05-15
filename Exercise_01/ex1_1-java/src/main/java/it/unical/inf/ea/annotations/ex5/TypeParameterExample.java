package it.unical.inf.ea.annotations.ex5;

public class TypeParameterExample<@TestTP T> {

  public void metodo(@TestTP T t) {
    System.out.println(t);
  }
}
