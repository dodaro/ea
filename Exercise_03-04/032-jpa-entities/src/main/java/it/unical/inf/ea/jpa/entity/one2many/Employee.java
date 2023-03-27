package it.unical.inf.ea.jpa.entity.one2many;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Employee {
 
    @Id
    private Long id;
 
    @OneToMany(mappedBy = "employeeCiao", fetch = FetchType.LAZY)
    private List<Email> emails;
}