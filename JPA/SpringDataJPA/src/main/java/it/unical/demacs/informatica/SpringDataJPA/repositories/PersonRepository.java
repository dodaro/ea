package it.unical.demacs.informatica.SpringDataJPA.repositories;

import it.unical.demacs.informatica.SpringDataJPA.domain.Person;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

// Creation of a Repository
public interface PersonRepository extends ListCrudRepository<Person, Long>, PagingAndSortingRepository<Person, Long> {
    // We do not write code!
    List<Person> findByLastName(String lastName);
    List<Person> findByFirstName(String firstName);
}
