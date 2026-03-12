package it.unical.inf.ea.uniprj;

import it.unical.inf.ea.uniprj.data.service.TeacherService;
import it.unical.inf.ea.uniprj.todo.aspect.annotation.TestMaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
@RequiredArgsConstructor
public class UniPrjApplication211 {

  public static void main(String[] args) {
    SpringApplication.run(UniPrjApplication211.class, args);
  }

  private final TestMaintenanceService testMaintenanceService;

  @Bean
  public void testMaintenanceAspect() {
    //testMaintenanceService.shipStuff();
  }


  private final TeacherService TeacherService;

  @Bean
  public void testTeacherServiceAspect() {
//    TeacherService.getAllTeacher();
  }
}
