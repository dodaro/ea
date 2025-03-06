package it.unical.inf.ea.app4;

import it.unical.inf.ea.app4.bean.DemoManager;
import it.unical.inf.ea.app4.config.ApplicationConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//The application is annotated with @ComponentScan. The basePackages option tells Spring to look for components in
// the it.unical.inf.asd package and its subpackages.
@ComponentScan(basePackages = "it.unical.inf.ea")
@Slf4j
public class Application4 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

//        DemoManager obj = (DemoManager) context.getBean("demoService");
        DemoManager obj = (DemoManager) context.getBean(DemoManager.class);

        log.info(obj.getServiceName());
    }
}
