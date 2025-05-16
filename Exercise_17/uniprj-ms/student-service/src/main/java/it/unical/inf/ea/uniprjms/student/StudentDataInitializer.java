package it.unical.inf.ea.uniprjms.student;

import it.unical.inf.ea.uniprjms.shared.dto.student.Gender;
import it.unical.inf.ea.uniprjms.student.data.dao.StudentDao;
import it.unical.inf.ea.uniprjms.student.data.entities.Address;
import it.unical.inf.ea.uniprjms.student.data.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class StudentDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(StudentDataInitializer.class);

    @Bean
    @Profile("!test")
    public CommandLineRunner initStudentData(StudentDao studentDao) {
        return args -> {
            if (studentDao.count() > 0) {
                logger.info("Student data already exists, skipping initialization");
                return;
            }

            logger.info("Initializing Student data...");

            List<Student> students = Arrays.asList(
                createStudent("Luca", "Ferrari", "luca.ferrari@email.com", 28.5, LocalDate.of(2000, 5, 15), Gender.MALE, true, 
                    new Address("Via Roma", "123", "Milano")),
                createStudent("Giulia", "Ricci", "giulia.ricci@email.com", 29.8, LocalDate.of(1999, 8, 22), Gender.FEMALE, false, 
                    new Address("Via Garibaldi", "45", "Roma")),
                createStudent("Marco", "Esposito", "marco.esposito@email.com", 26.3, LocalDate.of(2001, 3, 10), Gender.MALE, true, 
                    new Address("Corso Italia", "78", "Napoli")),
                createStudent("Sofia", "Russo", "sofia.russo@email.com", 30.0, LocalDate.of(1998, 11, 5), Gender.FEMALE, true, 
                    new Address("Via Dante", "22", "Torino")),
                createStudent("Andrea", "Conti", "andrea.conti@email.com", 27.2, LocalDate.of(2002, 1, 30), Gender.MALE, false, 
                    new Address("Via Mazzini", "56", "Bologna"))
            );

            studentDao.saveAll(students);
            logger.info("Student data initialized with {} records", students.size());
        };
    }

    private Student createStudent(String firstName, String lastName, String email,
                               double examAvgGrade, LocalDate birthDate, Gender gender,
                               boolean wantsNewsletter, Address address) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setExamAverageGrade(examAvgGrade);
        student.setBirthDate(birthDate);
        student.setGender(gender);
        student.setWantsNewsletter(wantsNewsletter);
        student.setAddress(address);
        return student;
    }
}