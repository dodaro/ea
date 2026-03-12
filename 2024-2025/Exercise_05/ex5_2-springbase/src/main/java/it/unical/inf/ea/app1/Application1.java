package it.unical.inf.ea.app1;

import it.unical.inf.ea.app1.bean.MyDAOBean;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Application1 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("it.unical.inf.ea.app1");
        context.refresh();

        //Getting Bean by Class
        MyDAOBean myDAOBean = context.getBean(MyDAOBean.class);
        log.info(myDAOBean.toString());
    }
}