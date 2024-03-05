package it.unical.inf.ea.app1;

import it.unical.inf.ea.app1.bean.MyDAOBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application1 {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan("it.unical.inf.ea.app1");
    context.refresh();

    //Getting Bean by Class
    MyDAOBean myDAOBean = context.getBean(MyDAOBean.class);
    System.out.println(myDAOBean);
  }
}