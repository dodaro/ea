package it.unical.inf.ea.jpa.dbtest;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT", uniqueConstraints =
  @UniqueConstraint(columnNames = {"REGION_CODE", "RSS_NO"})
)
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "DATE")
    private LocalDateTime creationDate;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    //////////// UNIQUE CONSTRAINTS /////////////////
    @Basic(optional = false)
    @Column(name = "REGION_CODE")
    private Integer regionCode;

    @Basic(optional = false)
    @Column(name = "RSS_NO")
    private String rssNo;
}
