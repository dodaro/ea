package it.unical.inf.ea.uniprjms.student;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"it.unical.inf.ea.uniprjms.shared",
		"it.unical.inf.ea.uniprjms.student"
})
@OpenAPIDefinition(info =
@Info(title = "Student API", version = "1.0", description = "Documentation Employee API v1.0")
)
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
