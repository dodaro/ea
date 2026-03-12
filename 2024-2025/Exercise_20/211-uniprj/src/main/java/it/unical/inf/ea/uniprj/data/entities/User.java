package it.unical.inf.ea.uniprj.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "User")
@Data
@NoArgsConstructor
@EntityListeners(value = { AuditingEntityListener.class})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "EMAIL", updatable = false, unique = true)
  private String email;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "DELETED")
  private boolean deleted = false;

  @Column(name = "ACTIVE")
  private boolean active = false;

  @Column(name = "ROLES")
  private String roles; //separati da ,

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
