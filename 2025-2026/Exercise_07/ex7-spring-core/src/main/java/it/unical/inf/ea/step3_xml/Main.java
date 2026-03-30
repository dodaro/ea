package it.unical.inf.ea.step3_xml;

import it.unical.inf.ea.step2_injection.NotificationService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * STEP 3 - Spring IoC con configurazione XML.
 *
 * Spring legge step3-beans.xml e crea/assembla tutti gli oggetti per noi.
 * Il file XML mostra tre approcci:
 *
 * 1) Wiring esplicito: <constructor-arg ref="emailSender"/>
 *    -> Specifichiamo noi quale bean iniettare.
 *
 * 2) autowire="byType": Spring cerca un bean il cui tipo corrisponde
 *    al parametro del setter. Se ce n'e' piu' di uno -> errore!
 *
 * 3) autowire="constructor": come byType, ma per i parametri del costruttore.
 *
 * Questo e' l'approccio LEGACY. Lo si trova in progetti datati.
 * Da Spring 3+ si preferiscono le annotazioni (vedi step 4 e 5).
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== STEP 3: Spring XML Configuration ===\n");

        var context = new ClassPathXmlApplicationContext("step3-beans.xml");

        // 1) Wiring esplicito: il bean usa emailSender (dichiarato nel XML)
        System.out.println("--- Wiring esplicito (constructor-arg ref) ---");
        var notificationService = context.getBean("notificationServiceExplicit", NotificationService.class);
        notificationService.notifyUser("Benvenuto nel sistema!");

        // 2) Autowire byType: Spring ha trovato PushSender automaticamente
        System.out.println("\n--- Autowire byType ---");
        var orderService = context.getBean("notificationServiceByType", OrderService.class);
        orderService.placeOrder("Laptop");

        // 3) Autowire constructor: Spring ha iniettato PushSender nel costruttore
        System.out.println("\n--- Autowire constructor ---");
        var alertService = context.getBean("notificationServiceByConstructor", AlertService.class);
        alertService.alert("Sistema in manutenzione");

        context.close();
    }
}
