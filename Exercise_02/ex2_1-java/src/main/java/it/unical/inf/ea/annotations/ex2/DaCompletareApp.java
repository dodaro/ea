package it.unical.inf.ea.annotations.ex2;

import java.lang.reflect.*;
import java.util.*;

public class DaCompletareApp {
  public static void main(String[] args) throws Exception {

    Map<String, String> map = new HashMap<>();
    Method[] methods = Class.forName("it.unical.inf.ea.annotations.ex2.DaCompletareUso").getMethods();
    for (Method m : methods) {
      DaCompletare dc = null;
      if ((dc = m.getAnnotation(DaCompletare.class)) != null) {
        String descrizione = dc.descrizione();
        String assegnataA = dc.assegnataA();
        map.put(descrizione, assegnataA);
      }
    }
    publicaInIntranet(map);
  }

  public static void publicaInIntranet(Map<String, String> map) {
    Set<String> keys = map.keySet();
    for (String key : keys) {
      System.out.println("Descrizione = " + key + "; Assegnata a: " + map.get(key));
    }
  }
}