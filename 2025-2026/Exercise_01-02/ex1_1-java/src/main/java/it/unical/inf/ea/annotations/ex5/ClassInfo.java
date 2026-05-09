package it.unical.inf.ea.annotations.ex5;

import java.lang.annotation.*;

@Documented
/*
 *	L'annotazione @Inherited in Java funziona solo per le annotazioni applicate
 *	a livello di classe, non a livello di metodo.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassInfo {
	String author() default "me";
	String date();
	int revision() default 1;
	String comments();
}