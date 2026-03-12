package it.unical.inf.ea.uniprjms.course.data.service;

import it.unical.inf.ea.uniprjms.course.data.entities.CourseMaterial;
import it.unical.inf.ea.uniprjms.course.data.dao.CourseMaterialDao;
import lombok.RequiredArgsConstructor;
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
