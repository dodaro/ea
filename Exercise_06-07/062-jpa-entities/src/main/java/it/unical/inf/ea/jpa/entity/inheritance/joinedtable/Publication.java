package it.unical.inf.ea.jpa.entity.inheritance.joinedtable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity(name="JT_Publication")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Publication {
 
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", updatable = false, nullable = false)
        protected Long id;

        @Column
        protected String title;

        @Column(name = "version")
        private int version;

        @Column
        @Temporal(TemporalType.DATE)
        private Date publishingDate;
}
