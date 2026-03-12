package it.unical.inf.ea.app2.autowiring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class TestAutowire {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("autowiring/beans.xml");

        EmployeeBean employee = (EmployeeBean) context.getBean("employee");
        log.info(employee.getFullName());
        log.info(employee.getDepartmentBean().getName());
    }
}