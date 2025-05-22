package it.unical.inf.ea.uniprjms.ms.student;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Student API", version = "1.0", description = "Documentation Employee API v1.0")
)
public class StudentAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAuthApplication.class, args);
	}

}
