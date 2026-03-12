package it.unical.inf.ea.uniprjms.ms.course.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

@Configuration
    public class Internationalization /*extends WebMvcConfigurerAdapter*/ {

        @Bean
        public AcceptHeaderLocaleResolver localeResolver() {
            final LanguageResolver resolver = new LanguageResolver();
            resolver.setSupportedLocales(Arrays.asList(Locale.ITALY, Locale.US,Locale.UK));
            resolver.setDefaultLocale(Locale.ITALY);
            return resolver;
        }
    
        @Bean
        public ResourceBundleMessageSource messageSource() {
            final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            source.setBasename("language/messages");
            source.setDefaultEncoding("UTF-8");
            return source;
        }
    }  