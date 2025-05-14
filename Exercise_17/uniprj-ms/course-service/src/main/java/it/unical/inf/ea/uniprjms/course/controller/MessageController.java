package it.unical.inf.ea.uniprjms.course.controller;

import it.unical.inf.ea.uniprjms.course.data.entities.Course;
import it.unical.inf.ea.uniprjms.course.data.service.CourseService;
import it.unical.inf.ea.uniprjms.course.service.CourseEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final CourseEventProducer courseEventProducer;
    private final CourseService courseService;

    public MessageController(CourseEventProducer courseEventProducer, CourseService courseService) {
        this.courseEventProducer = courseEventProducer;
        this.courseService = courseService;
    }

    // Endpoint 1: Invia una notifica per un corso esistente
    @PostMapping("/notify-course/{courseId}")
    public ResponseEntity<String> notifyCourse(@PathVariable Long courseId) {
        Course course = courseService.getById(courseId);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        String messageId = courseEventProducer.publishCourseCreatedEvent(course);
        return ResponseEntity.ok("Messaggio inviato con ID: " + messageId);
    }

    // Endpoint 2: Invia una notifica di aggiornamento per un corso esistente
    @PostMapping("/notify-course-update/{courseId}")
    public ResponseEntity<String> notifyCourseUpdate(@PathVariable Long courseId) {
        Course course = courseService.getById(courseId);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        String messageId = courseEventProducer.publishCourseUpdatedEvent(course);
        return ResponseEntity.ok("Messaggio di aggiornamento inviato con ID: " + messageId);
    }

    // Endpoint 3: Crea un corso di test e invia una notifica
    @PostMapping("/test-notification")
    public ResponseEntity<String> createTestNotification(@RequestBody Course courseData) {
        // Salva il corso (opzionale)
        Course savedCourse = courseService.save(courseData);

        // Invia la notifica
        String messageId = courseEventProducer.publishCourseCreatedEvent(savedCourse);

        return ResponseEntity.ok("Corso creato e notifica inviata con ID: " + messageId);
    }
}
