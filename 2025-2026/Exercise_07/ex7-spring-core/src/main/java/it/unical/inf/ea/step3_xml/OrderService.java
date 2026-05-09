package it.unical.inf.ea.step3_xml;

import it.unical.inf.ea.step2_injection.MessageSender;

/**
 * Servizio di esempio per dimostrare autowire="byType".
 * Ha un setter per MessageSender: Spring cerchera' un bean di quel tipo.
 *
 * NB: usa PushSender (unico MessageSender senza ambiguita' in questo scope).
 */
public class OrderService {

    private MessageSender sender;

    // Setter usato da Spring per l'autowiring byType
    public void setSender(MessageSender sender) {
        this.sender = sender;
    }

    public void placeOrder(String item) {
        sender.send("Ordine confermato: " + item);
    }
}
