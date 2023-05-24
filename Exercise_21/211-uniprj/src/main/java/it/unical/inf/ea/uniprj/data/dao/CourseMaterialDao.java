package it.unical.inf.ea.uniprj.data.dao;

import it.unical.inf.ea.uniprj.data.entities.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialDao extends JpaRepository<CourseMaterial, Long> {
}
