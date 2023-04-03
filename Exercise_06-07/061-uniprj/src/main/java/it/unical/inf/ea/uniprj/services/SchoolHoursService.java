package it.unical.inf.ea.uniprj.services;

import it.unical.inf.ea.uniprj.data.dao.CourseDao;
import it.unical.inf.ea.uniprj.data.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolHoursService {

  @Autowired
  private StudentDao studentDao;

  @Autowired
  private CourseDao courseDao;

  public String make() {
    //...
    return null;
  }
}
