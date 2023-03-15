package it.unical.inf.ea.annotation2.service;

import it.unical.inf.ea.annotation2.ext.ManagerBean;
import it.unical.inf.ea.annotation2.dao.StudentDAO;
import it.unical.inf.ea.annotation2.dto.StudentDTO;
import it.unical.inf.ea.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

  @Autowired
  @Qualifier("studentDao2")
  StudentDAO studentDAO;

  @Autowired
  ManagerBean managerBean;

  @Autowired
  Country country;

  @Override
  public StudentDTO createNewStudent() {
    StudentDTO studentDTO = studentDAO.createNewStudent();
    return managerBean.change(studentDTO);
  }
}
