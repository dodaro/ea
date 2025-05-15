package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.data.dao.CourseMaterialDao;
import it.unical.inf.ea.uniprj.data.entities.CourseMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {

  @Autowired
  private CourseMaterialDao courseMaterialDao;

  @Override
  public CourseMaterial save(CourseMaterial courseMaterial) {
    return courseMaterialDao.save(courseMaterial);
  }
}
