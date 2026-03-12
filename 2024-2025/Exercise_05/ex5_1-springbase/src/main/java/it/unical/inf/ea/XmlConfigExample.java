package it.unical.inf.ea;

import it.unical.inf.ea.app1.bean.Department;
import it.unical.inf.ea.app1.bean.Employee;
import it.unical.inf.ea.app1.bean.Operations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class XmlConfigExample {
    public static void main(String[] args) {
        @SuppressWarnings("resource") ApplicationContext ctx = new ClassPathXmlApplicationContext("xml/beans.xml");

        Employee employee = (Employee) ctx.getBean("employee");
        log.info(employee.toString());

        Department department = ctx.getBean(Department.class);
        Operations operations = ctx.getBean(Operations.class);

        log.info(department.toString());
        log.info(operations.toString());

        operations.helloWorld();
    }
}