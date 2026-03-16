package it.unical.inf.ea.annotations.ex7;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface RuoliUtente {
    Ruolo[] value();
}