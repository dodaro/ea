package it.unical.inf.ea.app4.config;

import it.unical.inf.ea.app4.bean.DemoManager;
import it.unical.inf.ea.app4.bean.DemoManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean(name = "demoService")
    public DemoManager helloWorld() {
        return new DemoManagerImpl();
    }
}
