package it.unical.inf.ea;

import it.unical.inf.ea.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class SpringXMLConfigurationMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Country countryBean = (Country) ac.getBean("country");
        String name = countryBean.getName();
        log.info("Country name: " + name);
        long population = countryBean.getPopulation();
        log.info("Country population: " + population);
        ac.close();
    }
}
