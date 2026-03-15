package it.unical.inf.ea.exe;

import lombok.Getter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LoggerManager {

  private final List<Logger> loggers = new ArrayList<>();

  public void init() {

    loggers.add(new ConsoleLog());
    final ExtensibleClassLoader cl = new ExtensibleClassLoader(Thread.currentThread().getContextClassLoader());
    final File dir = new File("plugins");
    final String[] files = dir.list();
    for (final String f : files)
    {
      if (!f.endsWith(".jar"))
      {
        continue;
      }
      try
      {
        cl.addURL(new File(dir, f).toURI().toURL());
        final Class<? > class1 = cl.loadClass(f.substring(0, f.length() - 4));
        if (Logger.class.isAssignableFrom(class1))
        {
          final Logger instance = (Logger) class1.getDeclaredConstructor().newInstance();
          instance.setName(class1.getSimpleName());
          loggers.add(instance);
        }
      }
      catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
      {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    LoggerManager logManager = new LoggerManager();
    logManager.init();
    logManager.getLoggers().forEach(x->System.out.println(x.getName()));
  }

}
