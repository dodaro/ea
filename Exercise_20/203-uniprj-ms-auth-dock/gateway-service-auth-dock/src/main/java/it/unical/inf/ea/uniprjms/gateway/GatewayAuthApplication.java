package it.unical.inf.ea.uniprjms.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class GatewayAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayAuthApplication.class, args);
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
