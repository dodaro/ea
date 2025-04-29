package it.unical.inf.ea.uniprj.config.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

/*
 * Configura Spring per usare la localizzazione (lingue supportate, file di messaggi)
 */
@Configuration
    public class Internationalization /*extends WebMvcConfigurerAdapter*/ {

        /*
         * Dice a Spring:
         * Quali lingue sono supportate (es. IT, US, UK)
         * Qual è la lingua predefinita se il client non ne specifica una
         */
        @Bean
        public AcceptHeaderLocaleResolver localeResolver() {
            final LanguageResolver resolver = new LanguageResolver();
            resolver.setSupportedLocales(Arrays.asList(Locale.ITALY, Locale.US,Locale.UK));
            resolver.setDefaultLocale(Locale.ITALY);
            return resolver;
        }

        /*
         * Dice a Spring:
         * Dove cercare i file .properties che contengono le traduzioni (messages_it.properties, messages_en.properties, ecc.)
         * Quale encoding usare
         */
        @Bean
        public ResourceBundleMessageSource messageSource() {
            final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            source.setBasename("language/messages");
            source.setDefaultEncoding("UTF-8");
            return source;
        }
    }  