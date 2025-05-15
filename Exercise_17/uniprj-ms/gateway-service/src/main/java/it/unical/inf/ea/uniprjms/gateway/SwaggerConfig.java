package it.unical.inf.ea.uniprjms.gateway;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi studentApi() {
        return GroupedOpenApi.builder()
                .group("student-api")
                .pathsToMatch("/student-api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi teacherApi() {
        return GroupedOpenApi.builder()
                .group("teacher-api")
                .pathsToMatch("/teacher-api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi courseApi() {
        return GroupedOpenApi.builder()
                .group("course-api")
                .pathsToMatch("/course-api/**")
                .build();
    }
}