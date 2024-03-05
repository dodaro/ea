package it.unical.inf.ea.exe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ConsoleLog implements Logger{

  private String name;

  @Override
  public void log(String s) {
    System.out.println(String.format("[%s] - %s", name, s));
  }

}
