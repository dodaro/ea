package it.unical.inf.ea.step4_javaconfig;

import it.unical.inf.ea.step2_injection.EmailSender;
import it.unical.inf.ea.step2_injection.MessageSender;
import it.unical.inf.ea.step2_injection.NotificationService;
import it.unical.inf.ea.step2_injection.SmsSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurazione Java: sostituisce il file XML con una classe Java.
 *
 * @Configuration indica a Spring che questa classe contiene definizioni di bean.
 * Ogni metodo @Bean e' equivalente a un <bean> nel file XML.
 *
 * Vantaggi rispetto a XML:
 * - Type-safe: errori a compile-time, non a runtime
 * - Refactoring-friendly: rinominare una classe aggiorna tutto
 * - Autocompletamento IDE
 */
@Configuration
public class AppConfig {

    @Bean
    public MessageSender emailSender() {
        return new EmailSender();
    }

    @Bean
    public MessageSender smsSender() {
        return new SmsSender();
    }

    @Bean
    public NotificationService notificationService() {
        // La dipendenza viene passata chiamando il metodo emailSender()
        // Spring si assicura che venga restituita sempre la stessa istanza (singleton)
        return new NotificationService(emailSender());
    }
}
