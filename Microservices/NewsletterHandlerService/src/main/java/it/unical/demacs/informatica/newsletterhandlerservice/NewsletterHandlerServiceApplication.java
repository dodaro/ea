package it.unical.demacs.informatica.newsletterhandlerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NewsletterHandlerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsletterHandlerServiceApplication.class, args);
    }

}
