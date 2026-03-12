package it.unical.inf.ae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "it.unical.inf.ae")
@EnableJpaRepositories(basePackages = "it.unical.inf.ae.data.dao")
@EntityScan(basePackages = "it.unical.inf.ae.shared.entity")
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}