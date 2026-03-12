package it.unical.inf.ea.uniprjms.course.data.dao;

import it.unical.inf.ea.uniprjms.course.data.entities.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialDao extends JpaRepository<CourseMaterial, Long> {
}
