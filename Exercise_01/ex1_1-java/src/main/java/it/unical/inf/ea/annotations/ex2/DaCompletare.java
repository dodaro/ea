package it.unical.inf.ea.annotations.ex2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DaCompletare { // Annotazione completa

  String descrizione(); // obbligatorio perché non c'è default

  String assegnataA() default "da assegnare";

  enum Priorita {ALTA, MEDIA, BASSA};
  Priorita priorita() default Priorita.ALTA;

}
