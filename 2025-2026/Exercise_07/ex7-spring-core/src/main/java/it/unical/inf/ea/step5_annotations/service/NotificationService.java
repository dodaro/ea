package it.unical.inf.ea.step5_annotations.service;

import it.unical.inf.ea.step5_annotations.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Service e' un alias semantico di @Component. Indica che questa classe
 * contiene logica di business. Funzionalmente e' identico a @Component,
 * ma rende il codice piu' leggibile (sappiamo che e' un servizio).
 *
 * @Autowired dice a Spring: "inietta automaticamente la dipendenza".
 * Siccome ci sono DUE implementazioni di MessageSender (EmailSender e SmsSender),
 * Spring non sa quale scegliere -> @Qualifier specifica il nome del bean.
 *
 * Al nome del bean nel container Spring. Di default il nome del bean è il nome della classe con l'iniziale minuscola:
 *
 *   - EmailSender → bean name "emailSender"
 *   - SmsSender → bean name "smsSender"
 *
 *   Quindi @Qualifier("emailSender") dice a Spring: "tra tutti i bean di tipo MessageSender, inietta quello che si chiama emailSender".
 *
 *   Si può anche dare un nome custom con @Component("myCustomName"), e poi usare @Qualifier("myCustomName").
 *
 * Prova a rimuovere @Qualifier: Spring lancera' un'eccezione di ambiguita'.
 * Prova a cambiare "myEmailSender" con "smsSender": cambiera' il canale di invio.
 */
@Service
public class NotificationService {

    private final MessageSender sender;

    @Autowired
    public NotificationService(@Qualifier("myEmailSender") MessageSender sender) { //
        this.sender = sender;
    }

    public void notifyUser(String message) {
        sender.send(message);
    }
}
