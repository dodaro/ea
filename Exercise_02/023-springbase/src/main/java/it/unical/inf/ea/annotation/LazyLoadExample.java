package it.unical.inf.ea.annotation;

import it.unical.inf.ea.annotation.bean.Employee;
import it.unical.inf.ea.annotation.controller.EmployeeController;
import it.unical.inf.ea.annotation.service.EmployeeManagerLazy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LazyLoadExample {
  @SuppressWarnings("resource")
  public static void main(String[] args) {
    //Method 1
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    //Method 2
//    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//    ctx.register(AppConfig.class);
//    ctx.refresh();

    System.out.println("Bean Factory Initialized !!");

    EmployeeController employeeController = ctx.getBean(EmployeeController.class);

    EmployeeManagerLazy empManager = employeeController.getLazyManagerInstance();

    Employee emp = empManager.create();

    System.out.println(emp);
  }
}