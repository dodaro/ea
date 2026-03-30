package it.unical.inf.ea.step1_problem;

/**
 * Servizio di notifica con accoppiamento stretto.
 *
 * PROBLEMA: questa classe crea internamente il suo EmailSender.
 * - Se voglio usare SMS? Devo modificare questa classe.
 * - Se voglio testare senza inviare email? Non posso sostituire il sender.
 * - Se ho 50 classi che fanno lo stesso? Devo modificarle tutte.
 */
public class NotificationService {

    // Dipendenza creata internamente -> accoppiamento stretto
    private final EmailSender sender = new EmailSender();

    public void notifyUser(String message) {
        sender.send(message);
    }
}
