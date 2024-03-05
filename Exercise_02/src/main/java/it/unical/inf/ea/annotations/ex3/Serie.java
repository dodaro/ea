package it.unical.inf.ea.annotations.ex3;

public @interface Serie { // a valore unico
  Alfabeto value();

  enum Alfabeto {A, B, C}

  ;
}