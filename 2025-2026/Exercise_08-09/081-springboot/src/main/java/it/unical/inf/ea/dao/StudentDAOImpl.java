package it.unical.inf.ea.dao;

import it.unical.inf.ea.dto.StudentDTO;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO {
    public StudentDTO createNewStudent() {
        StudentDTO e = new StudentDTO();
        e.setId(1);
        e.setFirstName("Mario");
        e.setLastName("Rossi");
        return e;
    }
}