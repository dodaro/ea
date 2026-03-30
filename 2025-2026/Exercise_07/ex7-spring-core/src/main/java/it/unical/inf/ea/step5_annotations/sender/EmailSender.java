package it.unical.inf.ea.step5_annotations.sender;

import org.springframework.stereotype.Component;

/**
 * @Component dice a Spring: "questa classe e' un bean, registrala nel container".
 * Il nome del bean sara' "emailSender" (nome della classe con iniziale minuscola).
 */
@Component("myEmailSender")
public class EmailSender implements MessageSender {

    @Override
    public void send(String message) {
        System.out.println("[EMAIL] Invio: " + message);
    }
}
