package it.unical.inf.ea.step5_annotations;

import it.unical.inf.ea.step5_annotations.service.NotificationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import it.unical.inf.ea.step5_annotations.config.AppConfig;

/**
 * STEP 5 - Spring con annotazioni e component scanning.
 *
 * Non dichiariamo piu' nessun bean manualmente.
 * Spring scansiona il package, trova le classi annotate con @Component/@Service,
 * e le registra automaticamente. Le dipendenze vengono iniettate con @Autowired.
 *
 * Confronta con gli step precedenti:
 * - Step 3 (XML): ogni bean e ogni dipendenza dichiarata in XML
 * - Step 4 (Java config): ogni bean dichiarato con @Bean in una classe @Configuration
 * - Step 5 (Annotations): Spring scopre tutto da solo, noi mettiamo solo le annotazioni
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== STEP 5: Annotations + Component Scanning ===\n");

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        var service = context.getBean(NotificationService.class);
        service.notifyUser("Benvenuto nel sistema!");

        context.close();
    }
}
