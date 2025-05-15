package it.unical.inf.ea;

import it.unical.inf.ea.controller.StudentController;
import it.unical.inf.ea.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		StudentController bean = ctx.getBean(StudentController.class);
		StudentDTO newStudent = bean.createNewStudent();

		log.info(newStudent.toString());
	}

}
