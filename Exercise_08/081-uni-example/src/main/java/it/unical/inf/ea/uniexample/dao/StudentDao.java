package it.unical.inf.ea.uniexample.dao;

import it.unical.inf.ea.uniexample.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student, Long> {}
