package it.unical.inf.ea.uniprj.data.service;

import it.unical.inf.ea.uniprj.data.dao.CourseMaterialDao;
import it.unical.inf.ea.uniprj.data.entities.CourseMaterial;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseMaterialServiceImpl implements CourseMaterialService {

  private final CourseMaterialDao courseMaterialDao;

  @Override
  public CourseMaterial save(CourseMaterial courseMaterial) {
    return courseMaterialDao.save(courseMaterial);
  }
}
