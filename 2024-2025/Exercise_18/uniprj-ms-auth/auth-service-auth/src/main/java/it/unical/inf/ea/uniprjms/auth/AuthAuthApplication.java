package it.unical.inf.ea.uniprjms.auth;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"it.unical.inf.ea.uniprjms.shared",
		"it.unical.inf.ea.uniprjms.auth"
})
@OpenAPIDefinition(info =
@Info(title = "Auth API", version = "1.0", description = "Documentation Employee API v1.0")
)
public class AuthAuthApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(AuthAuthApplication.class).run(args);
	}

}
