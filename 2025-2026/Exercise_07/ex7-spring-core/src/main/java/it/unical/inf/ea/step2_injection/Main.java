package it.unical.inf.ea.step2_injection;

/**
 * STEP 2 - Dependency Injection manuale (senza Spring).
 *
 * Il Main fa da "assembler": decide quale implementazione usare
 * e la passa a NotificationService. Il service non sa e non gli interessa
 * quale implementazione concreta sta usando.
 *
 * Vantaggio: posso cambiare da Email a SMS cambiando UNA riga qui.
 * Problema: in un'app con decine di classi e dipendenze incrociate,
 *           fare questo assemblaggio a mano diventa ingestibile.
 *           -> Serve un container che lo faccia per noi: Spring IoC.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== STEP 2: DI manuale (senza Spring) ===\n");

        // Assemblaggio manuale delle dipendenze
        MessageSender emailSender = new EmailSender();
        MessageSender smsSender = new SmsSender();

        // Posso scegliere quale sender usare senza modificare NotificationService
        NotificationService serviceViaEmail = new NotificationService(emailSender);
        NotificationService serviceViaSms = new NotificationService(smsSender);

        serviceViaEmail.notifyUser("Benvenuto nel sistema!");
        serviceViaSms.notifyUser("Benvenuto nel sistema!");
    }
}
