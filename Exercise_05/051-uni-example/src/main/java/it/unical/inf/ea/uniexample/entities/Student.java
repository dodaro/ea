package it.unical.inf.ea.uniexample.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STUDENT")
@Data
@NoArgsConstructor
public class Student {

    public enum Gender {
        MALE,
        FEMALE
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)  // blocked in The database layer
    @Column(name = "LASTNAME") // blocked at runtime and before contacting the database
    private String lastName;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date birthDateAsDate;

    @Column(name = "BIRTHDATE", insertable = false, updatable = false) //means that JPA won't include the column in the insert statement when saving the entity. But it will when updating the entity, and it will load it from the database.
    private LocalDate birthDateAsLocalDate;

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

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();


}
