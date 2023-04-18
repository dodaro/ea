package it.unical.demacs.informatica.newsletterhandlerservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    private final SendEmailService sendEmailService;

    public MessageListener(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @KafkaListener(id = "newsletterService", topics = "post")
    public void listen(String in) {
        sendEmailService.sendEmail(in);
    }
}
