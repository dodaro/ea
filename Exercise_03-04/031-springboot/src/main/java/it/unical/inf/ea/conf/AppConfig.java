package it.unical.inf.ea.conf;

import it.unical.inf.ea.ext.ManagerBean;
import it.unical.inf.ea.ext.ManagerBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean(name="managerBean")
  public ManagerBean helloWorld()
  {
    return new ManagerBeanImpl();
  }
}