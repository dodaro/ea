package it.unical.inf.ea.app3.config;

import it.unical.inf.ea.app3.bean.HelloMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // With @Configuration we declare that AppConfig is a configuration class.
@PropertySource(value= "app3/messages.properties") // The @PropertySource annotation allows us to use properties from the messages.properties file easily with @Value.
public class AppConfig {

    @Value("${mex}")
    private String message;

    // We inject the motd property into the message attribute.
    @Bean(name={"myMessage", "motd"})
    public HelloMessage helloMessageProducer() {

        var helloMessage = new HelloMessage(message);

        return helloMessage;
    }
}
