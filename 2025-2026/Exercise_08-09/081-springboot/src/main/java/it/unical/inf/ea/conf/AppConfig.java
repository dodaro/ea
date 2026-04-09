package it.unical.inf.ea.conf;

import it.unibo.ext.ManagerBean;
import it.unibo.ext.ManagerBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "managerBean")
    public ManagerBean helloWorld() {
        return new ManagerBeanImpl();
    }
}