package it.unical.inf.ea.annotations.ex7;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RuoliUtente.class)
@interface Ruolo {
    String value();
}