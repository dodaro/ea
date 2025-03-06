package it.unical.inf.ea.uniprjms.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigAuthApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigAuthApplication.class).run(args);
	}

}
