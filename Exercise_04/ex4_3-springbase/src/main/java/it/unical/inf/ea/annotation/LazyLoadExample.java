package it.unical.inf.ea.annotation;

import it.unical.inf.ea.annotation.bean.Employee;
import it.unical.inf.ea.annotation.controller.EmployeeController;
import it.unical.inf.ea.annotation.service.EmployeeManager;
import it.unical.inf.ea.annotation.service.EmployeeManagerLazy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@Slf4j
public class LazyLoadExample {
  @SuppressWarnings("resource")
  public static void main(String[] args) {
    //Method 1
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    //Method 2
//    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//    ctx.register(AppConfig.class);
//    ctx.refresh();

    log.info("Bean Factory Initialized !!");

    EmployeeController employeeController = ctx.getBean(EmployeeController.class);

    //differenze tra uno e l'altro
    EmployeeManager empManager = employeeController.getManagerInstance();
    //EmployeeManagerLazy empManager = employeeController.getLazyManagerInstance();

    Employee emp = empManager.create();

    log.info(emp.toString());
  }
}