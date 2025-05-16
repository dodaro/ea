package it.unical.inf.ea.uniprjms.teacher;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"it.unical.inf.ea.uniprjms.shared",
		"it.unical.inf.ea.uniprjms.teacher"
})
@OpenAPIDefinition(info =
@Info(title = "Teacher API", version = "1.0", description = "Documentation Teacher API v1.0")
)
public class TeacherAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherAuthApplication.class, args);
	}

}
