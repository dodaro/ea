package it.unical.inf.ea;

import it.unical.inf.ea.controller.StudentController;
import it.unical.inf.ea.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootStandaloneApplication implements CommandLineRunner {

	/* abilita
	 * @SpringBootApplication
	 * spring.main.web-application-type=none in application.properties
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStandaloneApplication.class, args);
	}

	@Autowired
	private StudentController studentController;


	@Override
	public void run(String... args) throws Exception {
		StudentDTO newStudent = studentController.createNewStudent();

		log.info(newStudent.toString());
	}
}