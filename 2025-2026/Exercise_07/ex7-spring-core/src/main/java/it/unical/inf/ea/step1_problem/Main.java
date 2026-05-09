package it.unical.inf.ea.step1_problem;

/**
 * STEP 1 - Il problema: accoppiamento stretto.
 *
 * Funziona, ma NotificationService e' legato a EmailSender.
 * Non posso cambiare il tipo di notifica senza modificare il codice sorgente.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== STEP 1: Accoppiamento stretto (no DI) ===\n");

        NotificationService service = new NotificationService();
        service.notifyUser("Benvenuto nel sistema!");

        // Domanda per lo studente:
        // Come faccio a inviare la stessa notifica via SMS senza modificare NotificationService?
        // Risposta: con questa struttura, non posso. -> Vedi step2
    }
}
