package it.unical.inf.ea.jpa.entity.one2one.mapsid;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
 
    @Id
    @Column(name = "id")
    private Long id;
 
    //...

    /*
    @MapsId tells Hibernate to use the id column of address as both primary-key and foreign-key.
    Also, notice that the @Id column of the Address entity no longer uses the @GeneratedValue annotation.
    The mappedBy attribute is now moved to the User class since the foreign key is now present in the address table.
     */
    @OneToOne
    @MapsId
    private User user;
   
    //... getters and setters
}



