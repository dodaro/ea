package it.unical.demacs.informatica.posthandlerservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class PostHandlerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostHandlerServiceApplication.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("post")
                .partitions(10)
                .replicas(1)
                .build();
    }

}
