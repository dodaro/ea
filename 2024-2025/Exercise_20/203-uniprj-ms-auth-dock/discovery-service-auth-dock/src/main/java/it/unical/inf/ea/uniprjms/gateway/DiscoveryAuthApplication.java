package it.unical.inf.ea.uniprjms.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryAuthApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DiscoveryAuthApplication.class).run(args);
	}

}
