package it.unical.inf.ea.step2_injection;

public class EmailSender implements MessageSender {

    @Override
    public void send(String message) {
        System.out.println("[EMAIL] Invio: " + message);
    }
}
