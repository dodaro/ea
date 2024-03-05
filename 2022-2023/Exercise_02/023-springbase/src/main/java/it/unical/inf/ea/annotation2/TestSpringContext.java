package it.unical.inf.ea.annotation2;

import it.unical.inf.ea.annotation2.conf.AppConfig;
import it.unical.inf.ea.annotation2.controller.StudentController;
import it.unical.inf.ea.annotation2.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpringContext
{
    public static void main(String[] args) 
    {
        //Method 1
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Method 2
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        ctx.register(AppConfig.class);
//        ctx.refresh();

        StudentController controller = (StudentController) context.getBean(StudentController.class);
         
        //OR this will also work
         
//        StudentController controller = (StudentController) context.getBean("studentController");
         
        System.out.println(controller.createNewStudent());
    }
}