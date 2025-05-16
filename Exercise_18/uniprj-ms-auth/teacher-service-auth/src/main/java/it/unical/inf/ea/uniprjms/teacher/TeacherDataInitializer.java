package it.unical.inf.ea.uniprjms.teacher;

import it.unical.inf.ea.uniprjms.teacher.data.dao.TeacherDao;
import it.unical.inf.ea.uniprjms.teacher.data.entities.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TeacherDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(TeacherDataInitializer.class);

    @Bean
    @Profile("!test")
    public CommandLineRunner initTeacherData(TeacherDao teacherDao) {
        return args -> {
            if (teacherDao.count() > 0) {
                logger.info("Teacher data already exists, skipping initialization");
                return;
            }

            logger.info("Initializing Teacher data...");

            List<Teacher> teachers = Arrays.asList(
                createTeacher("Mario", "Rossi"),
                createTeacher("Laura", "Bianchi"),
                createTeacher("Giuseppe", "Verdi"),
                createTeacher("Francesca", "Neri"),
                createTeacher("Alessandro", "Manzoni")
            );

            teacherDao.saveAll(teachers);
            logger.info("Teacher data initialized with {} records", teachers.size());
        };
    }

    private Teacher createTeacher(String firstName, String lastName) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        return teacher;
    }
}