package it.unical.inf.ea.jpa.dbtest;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Email {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeCol")
    private Employee employeeField;

}
