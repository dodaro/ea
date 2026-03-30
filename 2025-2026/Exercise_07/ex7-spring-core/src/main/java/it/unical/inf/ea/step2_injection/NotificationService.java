package it.unical.inf.ea.step2_injection;

/**
 * Servizio di notifica con Dependency Injection manuale.
 *
 * Ora la dipendenza arriva dall'esterno via costruttore (Constructor Injection).
 * NotificationService non sa se sta usando Email o SMS -> disaccoppiamento!
 */
public class NotificationService {

    private final MessageSender sender;

    // La dipendenza viene "iniettata" dal chiamante
    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void notifyUser(String message) {
        sender.send(message);
    }
}
