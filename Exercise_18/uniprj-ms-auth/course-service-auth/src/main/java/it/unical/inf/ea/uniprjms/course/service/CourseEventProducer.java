package it.unical.inf.ea.uniprjms.course.service;

import it.unical.inf.ea.uniprjms.course.data.entities.Course;
import it.unical.inf.ea.uniprjms.shared.dto.course.CourseDto;
import it.unical.inf.ea.uniprjms.shared.dto.mq.EventDto;
import it.unical.inf.ea.uniprjms.shared.config.RabbitMQConstants;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CourseEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(CourseEventProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final ModelMapper modelMapper;

    public CourseEventProducer(RabbitTemplate rabbitTemplate, ModelMapper modelMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.modelMapper = modelMapper;
    }

    public String publishCourseCreatedEvent(Course course) {
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);

        String messageId = UUID.randomUUID().toString();

        EventDto<CourseDto> event = new EventDto<>();
        event.setId(messageId);
        event.setEventType("COURSE_CREATED");
        event.setTimestamp(LocalDateTime.now());
        event.setData(courseDto);

        logger.info("Publishing course created event with ID: {}", messageId);

        rabbitTemplate.convertAndSend(
                RabbitMQConstants.MAIN_EXCHANGE,
                RabbitMQConstants.COURSE_CREATED_KEY,
                event
        );

        logger.info("Course created event published successfully");

        return messageId;
    }

    public String publishCourseUpdatedEvent(Course course) {
        CourseDto courseDto = modelMapper.map(course, CourseDto.class);

        String messageId = UUID.randomUUID().toString();

        EventDto<CourseDto> event = new EventDto<>();
        event.setId(messageId);
        event.setEventType("COURSE_UPDATED");
        event.setTimestamp(LocalDateTime.now());
        event.setData(courseDto);

        logger.info("Publishing course updated event with ID: {}", messageId);

        rabbitTemplate.convertAndSend(
                RabbitMQConstants.MAIN_EXCHANGE,
                RabbitMQConstants.COURSE_UPDATED_KEY,
                event
        );

        logger.info("Course updated event published successfully");

        return messageId;
    }
}