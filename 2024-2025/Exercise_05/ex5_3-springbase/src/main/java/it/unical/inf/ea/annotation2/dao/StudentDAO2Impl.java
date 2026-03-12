package it.unical.inf.ea.annotation2.dao;

import it.unical.inf.ea.annotation2.dto.StudentDTO;
import org.springframework.stereotype.Repository;

@Repository("studentDao2")
public class StudentDAO2Impl implements StudentDAO
{
    public StudentDTO createNewStudent()
    {
        StudentDTO e = new StudentDTO();
        e.setId(1);
        e.setFirstName("Ciccio");
        e.setLastName("Pasticcio");
        return e;
    }
}