package it.unical.inf.ea.app2.autowiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutowire {
  public static void main(String[] args) {
    @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("autowiring/beans.xml");

    EmployeeBean employee = (EmployeeBean) context.getBean("employee");
    System.out.println(employee.getFullName());
    System.out.println(employee.getDepartmentBean().getName());
  }
}