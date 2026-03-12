package it.unical.inf.ea.uniprjms.course;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// per scansionare
@ComponentScan(basePackages = {
		"it.unical.inf.ea.uniprjms.shared",
		"it.unical.inf.ea.uniprjms.course"
})
@EnableFeignClients
@OpenAPIDefinition(info =
@Info(title = "Course API", version = "1.0", description = "Documentation Course API v1.0")
)
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

}
