package it.unical.inf.ea.uniprjms.teacher.service;

import it.unical.inf.ea.uniprjms.domain.dto.course.CourseDto;
import it.unical.inf.ea.uniprjms.domain.dto.mq.EventDto;
import it.unical.inf.ea.uniprjms.shared.config.RabbitMQConstants;
import it.unical.inf.ea.uniprjms.teacher.controller.MessageStatsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CourseEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CourseEventConsumer.class);

    @RabbitListener(
        bindings = @QueueBinding(
            value = @Queue(RabbitMQConstants.TEACHER_QUEUE),
            exchange = @Exchange(name = RabbitMQConstants.MAIN_EXCHANGE, type = "topic"),
            key = {RabbitMQConstants.COURSE_CREATED_KEY, RabbitMQConstants.COURSE_UPDATED_KEY}
        )
    )
    public void handleCourseEvent(EventDto<CourseDto> event) {
        logger.info("<MQ> RICEVUTO MESSAGGIO DALLA CODA: ID Evento: {}, Tipo: {}", event.getId(), event.getEventType());
        logger.info("<MQ> Dettagli Corso: ID: {}, Titolo: {}",
                    event.getData().getId(), 
                    event.getData().getTitle());
        
        if ("COURSE_CREATED".equals(event.getEventType())) {
            logger.info("<MQ> Elaborazione notifica creazione nuovo corso: {}", event.getData().getTitle());
            handleCourseCreated(event);
        } else if ("COURSE_UPDATED".equals(event.getEventType())) {
            logger.info("<MQ> Elaborazione notifica aggiornamento corso: {}", event.getData().getTitle());
            handleCourseUpdated(event);
        }
        
        logger.info("<MQ> Elaborazione messaggio completata per evento: {}", event.getId());
    }
    
    private void handleCourseCreated(EventDto<CourseDto> event) {
        CourseDto courseDto = event.getData();
        logger.info("<MQ> Docente con ID {} è stato assegnato al nuovo corso: {}",
                    courseDto.getTeacherId(), 
                    courseDto.getTitle());
                    
        // Registra il messaggio nelle statistiche
        MessageStatsController.recordCourseCreated(event.getId(), courseDto.getTitle());
    }
    
    private void handleCourseUpdated(EventDto<CourseDto> event) {
        CourseDto courseDto = event.getData();
        logger.info("<MQ> Docente con ID {} è stato notificato dell'aggiornamento del corso: {}",
                    courseDto.getTeacherId(), 
                    courseDto.getTitle());
                    
        // Registra il messaggio nelle statistiche
        MessageStatsController.recordCourseUpdated(event.getId(), courseDto.getTitle());
    }
}