package it.unical.demacs.informatica.SpringDataJPA;

import it.unical.demacs.informatica.SpringDataJPA.domain.Person;
import it.unical.demacs.informatica.SpringDataJPA.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class TestPersistenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestPersistenceApplication.class, args);
    }
    @Bean
    public CommandLineRunner test(PersonRepository repository) {
        return (args) -> {
            repository.save(new Person("Mario", "Rossi", 18));
            repository.save(new Person("Simona", "Bianchi", 20));
            System.out.println("Totale persone: " + repository.count());
            repository.findAll().forEach(System.out::println);
            repository.findAll(Sort.by("lastName")).forEach(System.out::println);
            repository.findByFirstName("Mario").forEach(System.out::println);
            repository.deleteAll();
            System.out.println("Totale persone: " + repository.count());
        };
    }
}
