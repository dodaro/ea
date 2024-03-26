package it.unical.inf.ea.jpa.entity.one2many.other;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    
    @Id
    @GeneratedValue
    private Long id;
 
    @Column(nullable = false, length = 100)
    private String name;
 
    @Column(name = "email_address")
    private String emailAddress;

    /*
    CASCADE: any change happened on User must cascade to Address as well
    CascadeType.PERSIST : cascade type presist means that save() or persist() operations cascade to related entities.
    CascadeType.MERGE : cascade type merge means that related entities are merged when the owning entity is merged.
    CascadeType.REFRESH : cascade type refresh does the same thing for the refresh() operation.
    CascadeType.REMOVE : cascade type remove removes all related entities association with this setting when the owning entity is deleted.
    CascadeType.DETACH : cascade type detach detaches all related entities if a “manual detach” occurs.
    CascadeType.ALL : cascade type all is shorthand for all of the above cascade operations.
     */
    @OneToMany(mappedBy = "myaccount", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AccountSetting> accountSettings = new ArrayList<>();
 
    // getters and setters
}










