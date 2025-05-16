package it.unical.inf.ea.uniprjms.student.data.entities;

import it.unical.inf.ea.uniprjms.shared.dto.student.Gender;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(value = {AuditingEntityListener.class, StudentAuditTrailListener.class})
@Table(name = "STUDENT")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)  // blocked in The database layer
    @Column(name = "LASTNAME") // blocked at runtime and before contacting the database
    private String lastName;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "EXAM_AVERAGE_GRADE")
    private double examAverageGrade;

    @Column(name = "BIRTHDATE")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "WANT_NEWSLETTER")
    private boolean wantsNewsletter;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "ST_STREET")),
        @AttributeOverride(name = "number", column = @Column(name = "ST_NUMBER")),
        @AttributeOverride(name = "city", column = @Column(name = "ST_CITY"))
    })
    private Address address;


    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    private Long createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private Long lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;

}
