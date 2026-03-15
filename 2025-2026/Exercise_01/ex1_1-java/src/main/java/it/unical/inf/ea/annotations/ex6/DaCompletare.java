package it.unical.inf.ea.annotations.ex6;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({ TYPE, METHOD, CONSTRUCTOR, PACKAGE, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DaCompletare {
  String descrizione();

  String assegnataA() default "da assegnare";
}