package it.unical.inf.ea.jpa.dbtest;

import jakarta.persistence.*;

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
