package it.unical.inf.ea.app1.conf;

import it.unical.inf.ea.app1.bean.MyDAOBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfiguration {

	@Bean
	public MyDAOBean getMyDAOBean() {
		return new MyDAOBean();
	}
}