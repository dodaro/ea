package it.unical.inf.ea.jpa.entity.one2one;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idx")
    private Long id;
    //...

    /*
        We also need to place the @OneToOne annotation here, too.
        That's because this is a bidirectional relationship.
        The address side of the relationship is called the non-owning side.
     */
    @OneToOne(mappedBy = "addressField")
    private User user;
}