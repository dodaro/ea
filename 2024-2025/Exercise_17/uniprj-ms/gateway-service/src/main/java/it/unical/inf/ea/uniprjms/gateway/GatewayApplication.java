package it.unical.inf.ea.uniprjms.gateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
/*
//	@Autowired
	private final RouteDefinitionLocator locator;

	@Bean
	public List<GroupedOpenApi> apis() {
		List<GroupedOpenApi> groups = new ArrayList<>();
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		assert definitions != null;
		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
			String name = routeDefinition.getId().replaceAll("-service", "");
			groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
		});
		return groups;
	}
*/
}
