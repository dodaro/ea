package it.unical.inf.ea.exe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class FileLogger implements Logger {

  private String name;

  @Override
  public void log(String mex) {
    // scrivo su file
  }

}
