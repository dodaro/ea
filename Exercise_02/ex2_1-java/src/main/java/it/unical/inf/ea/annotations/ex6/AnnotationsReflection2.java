package it.unical.inf.ea.annotations.ex6;

import java.lang.annotation.Annotation;

public class AnnotationsReflection2 {
  public static void main(String[] args) throws Exception {
    Annotation[] dcs = SottoClasse.class.getAnnotations();
    for (Annotation dc : dcs) {
      System.out.println(dc);
    }
  }
}