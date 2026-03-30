package it.unical.inf.ea.step5_annotations.sender;

import org.springframework.stereotype.Component;

@Component
public class SmsSender implements MessageSender {

    @Override
    public void send(String message) {
        System.out.println("[SMS] Invio: " + message);
    }
}
