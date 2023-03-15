package it.unical.inf.ea;

import it.unical.inf.ea.app1.bean.Department;
import it.unical.inf.ea.app1.bean.Employee;
import it.unical.inf.ea.app1.bean.Operations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConfigExample
{
	public static void main( String[] args )
    {
		@SuppressWarnings("resource") ApplicationContext ctx = new ClassPathXmlApplicationContext("xml/beans.xml");
        
        Employee employee = ctx.getBean(Employee.class);
         
        Department department = ctx.getBean(Department.class);
         
        Operations operations = ctx.getBean(Operations.class);
 
        System.out.println(department);
        System.out.println(employee);
 
        operations.helloWorld();
    }
}