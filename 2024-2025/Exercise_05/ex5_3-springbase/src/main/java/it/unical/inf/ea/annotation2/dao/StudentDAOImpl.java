package it.unical.inf.ea.annotation2.dao;

import it.unical.inf.ea.annotation2.dto.StudentDTO;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
//@Primary
public class StudentDAOImpl implements StudentDAO
{
    public StudentDTO createNewStudent()
    {
        StudentDTO e = new StudentDTO();
        e.setId(1);
        e.setFirstName("Mario");
        e.setLastName("Rossi");
        return e;
    }
}