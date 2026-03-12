package it.unical.inf.ea.uniprjms.shared.config;

public class RabbitMQConstants {
    // Exchange
    public static final String MAIN_EXCHANGE = "uniprj-exchange";
    
    // Queues
    public static final String COURSE_QUEUE = "course-queue";
    public static final String STUDENT_QUEUE = "student-queue";
    public static final String TEACHER_QUEUE = "teacher-queue";
    
    // Routing Keys
    public static final String COURSE_CREATED_KEY = "course.created";
    public static final String COURSE_UPDATED_KEY = "course.updated";
    public static final String STUDENT_REGISTERED_KEY = "student.registered";
    public static final String TEACHER_ASSIGNED_KEY = "teacher.assigned";
}