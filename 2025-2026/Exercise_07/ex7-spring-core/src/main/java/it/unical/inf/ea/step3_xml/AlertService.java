package it.unical.inf.ea.step3_xml;

import it.unical.inf.ea.step2_injection.MessageSender;

/**
 * Servizio di esempio per dimostrare autowire="constructor".
 * Spring inietta automaticamente il bean che matcha il tipo del parametro costruttore.
 */
public class AlertService {

    private final MessageSender sender;

    public AlertService(MessageSender sender) {
        this.sender = sender;
    }

    public void alert(String message) {
        sender.send("ALERT: " + message);
    }
}
