package it.unical.inf.ea.uniprjms.course;

import it.unical.inf.ea.uniprjms.course.client.TeacherClient;
import it.unical.inf.ea.uniprjms.course.data.dao.CourseDao;
import it.unical.inf.ea.uniprjms.course.data.dao.CourseMaterialDao;
import it.unical.inf.ea.uniprjms.course.data.entities.Course;
import it.unical.inf.ea.uniprjms.course.data.entities.CourseMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CourseDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(CourseDataInitializer.class);

    @Bean
    @Profile("!test")
    public CommandLineRunner initCourseData(CourseDao courseDao, CourseMaterialDao courseMaterialDao, TeacherClient teacherClient) {
        return args -> {
            if (courseDao.count() > 0) {
                logger.info("Course data already exists, skipping initialization");
                return;
            }

            logger.info("Initializing Course and CourseMaterial data...");

            // In un sistema reale, potresti ottenere gli ID degli insegnanti tramite il client Feign
            // List<TeacherBasicDto> teachers = teacherClient.findAllTeachers();
            // Per semplicità, userò ID fissi
            List<Long> teacherIds = Arrays.asList(1L, 2L, 3L, 4L, 5L);

            // Crea e salva i corsi
            List<Course> courses = Arrays.asList(
                createCourse("Programmazione Java", teacherIds.size() > 0 ? teacherIds.get(0) : null),
                createCourse("Database Management", teacherIds.size() > 1 ? teacherIds.get(1) : null),
                createCourse("Architetture Software", teacherIds.size() > 2 ? teacherIds.get(2) : null),
                createCourse("Intelligenza Artificiale", teacherIds.size() > 3 ? teacherIds.get(3) : null),
                createCourse("Sviluppo Web Avanzato", teacherIds.size() > 4 ? teacherIds.get(4) : null)
            );

            courseDao.saveAll(courses);
            logger.info("Course data initialized with {} records", courses.size());

            // Crea e salva i materiali dei corsi
            List<CourseMaterial> courseMaterials = Arrays.asList(
                createCourseMaterial(courses.get(0).getId(), "https://materials.example.com/java", "RSS001", "JAVA101"),
                createCourseMaterial(courses.get(1).getId(), "https://materials.example.com/db", "RSS002", "DB101"),
                createCourseMaterial(courses.get(2).getId(), "https://materials.example.com/arch", "RSS003", "ARCH101"),
                createCourseMaterial(courses.get(3).getId(), "https://materials.example.com/ai", "RSS004", "AI101"),
                createCourseMaterial(courses.get(4).getId(), "https://materials.example.com/web", "RSS005", "WEB101")
            );

            // Collega i corsi ai materiali
            for (int i = 0; i < courseMaterials.size(); i++) {
                courseMaterials.get(i).setCourse(courses.get(i));
            }

            courseMaterialDao.saveAll(courseMaterials);
            logger.info("CourseMaterial data initialized with {} records", courseMaterials.size());
        };
    }

    private Course createCourse(String title, Long teacherId) {
        Course course = new Course();
        course.setTitle(title);
        course.setTeacherId(teacherId);
        return course;
    }

    private CourseMaterial createCourseMaterial(Long id, String url, String rssNo, String courseCode) {
        CourseMaterial material = new CourseMaterial();
        material.setId(id);
        material.setUrl(url);
        material.setRss_no(rssNo);
        material.setCourse_code(courseCode);
        return material;
    }
}