package it.unical.inf.ea.annotations.ex5;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
/*
 *	L'annotazione @Inherited in Java funziona solo per le annotazioni applicate
 *	a livello di classe, non a livello di metodo.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo{
	String author() default "me";
	String date();
	int revision() default 1;
	String comments();
}