package it.unical.inf.ea.uniprj.data.dao;

import it.unical.inf.ea.uniprj.data.dto.StudentValue;
import it.unical.inf.ea.uniprj.data.entities.Course;
import it.unical.inf.ea.uniprj.data.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentDao
    extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

  @Query(value = "from Student s where s.lastName=:xxx")
  List<Student> ciccio(@Param("xxx") String lastname);

  int countByGender(Student.Gender gender);

  List<Student> findAllFirst3ByLastName(String name);

  List<Student> findAllByLastNameOrFirstName(String lastname, String f);

  List<Student> findAllByLastNameAndFirstName(String lastname, String f);

  List<Student> findAllByBirthDateBetweenOrderByLastNameDesc(LocalDate from, LocalDate to);

  List<Student> findAllByBirthDateLessThanEqualAndFirstNameIsNotNullAndLastNameStartingWithAndWantsNewsletterTrueOrderByGenderAsc(LocalDate date, String lastnameStart);

  @Query("SELECT s.gender AS gender, COUNT(s.id) AS count "
      + "FROM Student AS s "
      + "GROUP BY s.gender ORDER BY s.gender")
  List<StudentValue> countByGenger();

  // 091
  Page<Student> findAllByLastName(String name, Pageable pageable);

}
