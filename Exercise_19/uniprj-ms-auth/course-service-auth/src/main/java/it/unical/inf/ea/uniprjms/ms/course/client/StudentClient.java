package it.unical.inf.ea.uniprjms.ms.course.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "student-service2")
public interface StudentClient {

	@GetMapping("/student-api/students")
	List<Object> all();
	
}
