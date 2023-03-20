package it.unical.inf.ea.jpa;

import it.unical.inf.ea.jpa.dao.CustomerDao;
import it.unical.inf.ea.jpa.entities.other.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDaoTest {
    
    @Autowired
    private CustomerDao customerRepository;
    
    @Test
    public void whenFindingCustomerById_thenCorrect() {
        customerRepository.save(new Customer("John", "john@domain.com"));
        assertThat(customerRepository.findById(1L)).isInstanceOf(Optional.class);
    }
    
    @Test
    public void whenFindingAllCustomers_thenCorrect() {
        customerRepository.save(new Customer("John", "john@domain.com"));
        customerRepository.save(new Customer("Julie", "julie@domain.com"));
        Iterable<Customer> all = customerRepository.findAll();
        Iterator<Customer> iterator = all.iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());

        }
        assertThat(customerRepository.findAll()).isInstanceOf(List.class);
    }
}