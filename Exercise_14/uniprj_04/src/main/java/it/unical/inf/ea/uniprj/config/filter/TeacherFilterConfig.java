package it.unical.inf.ea.uniprj.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherFilterConfig {

    @Bean
    public FilterRegistrationBean<TeacherApiFilter> studentFilterRegistration(TeacherApiFilter filter) {
        FilterRegistrationBean<TeacherApiFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.addUrlPatterns("/teacher-api/*"); // Filtra solo queste URL
        registration.setOrder(1); // Priorità (più basso = eseguito prima)
        return registration;
    }
}