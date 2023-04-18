package it.unical.inf.ea.service;

import it.unical.inf.ea.dao.StudentDAO;
import it.unical.inf.ea.dto.StudentDTO;
import it.unical.inf.ea.ext.ManagerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  StudentDAO dao;

  @Autowired
  ManagerBean managerBean;

  @Override
  public StudentDTO createNewStudent() {
    StudentDTO studentDTO = dao.createNewStudent();
    return managerBean.change(studentDTO);
  }
}
