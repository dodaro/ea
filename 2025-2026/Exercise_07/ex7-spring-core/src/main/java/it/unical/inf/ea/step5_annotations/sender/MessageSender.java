package it.unical.inf.ea.step5_annotations.sender;

/**
 * Stessa interfaccia di step2, ridefinita qui con le annotazioni Spring.
 */
public interface MessageSender {

    void send(String message);
}
