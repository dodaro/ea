package it.unical.inf.ea.jpa.dao;

import it.unical.inf.ea.jpa.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student, Long> {}
