package it.unical.inf.ea.uniprjms.teacher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/message-stats")
public class MessageStatsController {

    private static final Logger logger = LoggerFactory.getLogger(MessageStatsController.class);
    
    // Contatori per messaggi ricevuti
    private static final AtomicInteger coursesCreatedCount = new AtomicInteger(0);
    private static final AtomicInteger coursesUpdatedCount = new AtomicInteger(0);
    
    // Salva gli ultimi 10 messaggi ricevuti con timestamp
    private static final Map<String, String> lastMessages = new ConcurrentHashMap<>();
    
    // Metodo per incrementare il contatore dei corsi creati
    public static void recordCourseCreated(String messageId, String courseTitle) {
        coursesCreatedCount.incrementAndGet();
        lastMessages.put(messageId, "CREATED: " + courseTitle + " at " + System.currentTimeMillis());
        if (lastMessages.size() > 10) {
            // Rimuovi il messaggio più vecchio se ne abbiamo più di 10
            String oldestKey = lastMessages.keySet().iterator().next();
            lastMessages.remove(oldestKey);
        }
    }
    
    // Metodo per incrementare il contatore dei corsi aggiornati
    public static void recordCourseUpdated(String messageId, String courseTitle) {
        coursesUpdatedCount.incrementAndGet();
        lastMessages.put(messageId, "UPDATED: " + courseTitle + " at " + System.currentTimeMillis());
        if (lastMessages.size() > 10) {
            String oldestKey = lastMessages.keySet().iterator().next();
            lastMessages.remove(oldestKey);
        }
    }
    
    @GetMapping
    public Map<String, Object> getMessageStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("coursesCreatedCount", coursesCreatedCount.get());
        stats.put("coursesUpdatedCount", coursesUpdatedCount.get());
        stats.put("totalMessagesReceived", coursesCreatedCount.get() + coursesUpdatedCount.get());
        stats.put("lastMessages", lastMessages);
        
        logger.info("Message stats requested: {}", stats);
        
        return stats;
    }
}