package it.unical.inf.ea.uniprjms.ms.course.client;

import it.unical.inf.ea.uniprjms.ms.course.dto.TeacherBasicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "teacher-service")
public interface TeacherClient {

	@GetMapping("/teacher-api/teachers/{id}")
	TeacherBasicDto findById(@PathVariable("id") Long teacherId);
	
}
