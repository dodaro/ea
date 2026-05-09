package it.unical.inf.ea.step2_injection;

/**
 * Interfaccia che astrae il meccanismo di invio.
 * Qualsiasi classe che sa inviare un messaggio implementa questa interfaccia.
 */
public interface MessageSender {

    void send(String message);
}
