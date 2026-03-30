package it.unical.inf.ea.step3_xml;

import it.unical.inf.ea.step2_injection.MessageSender;

/**
 * Terza implementazione di MessageSender per notifiche push.
 * Usata negli esempi di autowire XML per evitare ambiguita'
 * (emailSender e smsSender sono dello stesso tipo).
 */
public class PushSender implements MessageSender {

    @Override
    public void send(String message) {
        System.out.println("[PUSH] Invio: " + message);
    }
}
