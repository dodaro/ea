package it.unical.inf.ea.step4_javaconfig;

import it.unical.inf.ea.step2_injection.NotificationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * STEP 4 - Spring IoC con configurazione Java (@Configuration + @Bean).
 *
 * Stesso risultato dello step 3, ma senza XML.
 * La classe AppConfig definisce i bean e le loro dipendenze in Java puro.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== STEP 4: Java Configuration (@Configuration + @Bean) ===\n");

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        var service = context.getBean(NotificationService.class);
        service.notifyUser("Benvenuto nel sistema!");

        context.close();
    }
}
