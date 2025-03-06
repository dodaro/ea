package it.unical.inf.ea.auth.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
  // TESTA CON test-cors/cors.html

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
//        .allowedOrigins("http://localhost:8080")
//        .allowedMethods(CorsConfiguration.ALL)
//        .allowedMethods("/**")
        .allowedMethods("POST", "OPTIONS", "GET", "DELETE", "PUT")
        .allowedHeaders("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization")
        .maxAge(3600)
        .exposedHeaders("X-Get-Header");
    registry.addMapping("/**");
  }
}