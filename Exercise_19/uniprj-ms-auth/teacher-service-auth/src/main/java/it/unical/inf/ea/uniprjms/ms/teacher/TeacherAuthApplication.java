package it.unical.inf.ea.uniprjms.ms.teacher;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Teacher API", version = "1.0", description = "Documentation Teacher API v1.0")
)
public class TeacherAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherAuthApplication.class, args);
	}

}
