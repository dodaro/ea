package it.unical.inf.ea.store;

import it.unical.inf.ea.store.data.entities.Customer;
import it.unical.inf.ea.store.data.entities.dao.CustomerDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);

		CustomerDao customerDao = context.getBean(CustomerDao.class);
		List<Customer> all = customerDao.findAll();
		System.out.println(all.size());
	}
}
