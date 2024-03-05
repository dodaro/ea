package it.unical.inf.ea.app3;

import it.unical.inf.ea.app3.bean.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//The application is annotated with @ComponentScan. The basePackages option tells Spring to look for components in
// the it.unical.inf.ea package and its subpackages.
@ComponentScan(basePackages = "it.unical.inf.ea.app3")
public class Application3 {

    private static final Logger logger = LoggerFactory.getLogger(Application3.class);

    public static void main(String[] args) {

        // AnnotationConfigApplicationContext is a Spring standalone application context.
        // It accepts the annotated Application as an input; thus the scanning is enabled.
        var ctx = new AnnotationConfigApplicationContext(Application3.class);

        var msgBean1 = ctx.getBean(HelloMessage.class);
        logger.info("{}", msgBean1.getMessage());

        var msgBean2 = (HelloMessage) ctx.getBean("myMessage");
        logger.info("{}", msgBean2.getMessage());

        var msgBean3 = (HelloMessage) ctx.getBean("motd");
        logger.info("{}", msgBean3.getMessage());

        ctx.close();
    }
}