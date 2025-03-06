package it.unical.inf.ea.uniprj.config;

import it.unical.inf.ea.uniprj.config.filter.GenericServletInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new GenericServletInterceptor()).order(Ordered.LOWEST_PRECEDENCE);
    //    WebMvcConfigurer.super.addInterceptors(registry);
  }
}
