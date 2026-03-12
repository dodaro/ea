package it.unical.inf.ea.uniprj.data.dao;

import it.unical.inf.ea.uniprj.data.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LessonDao extends JpaRepository<Lesson, UUID> {


}
