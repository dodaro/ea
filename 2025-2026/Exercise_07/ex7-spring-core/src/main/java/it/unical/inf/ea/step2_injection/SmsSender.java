package it.unical.inf.ea.step2_injection;

public class SmsSender implements MessageSender {

    @Override
    public void send(String message) {
        System.out.println("[SMS] Invio: " + message);
    }
}
