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
        @MapsId  è utilizzata per stabilire una relazione di mapping uno a uno tra due entità,
        in cui l'ID di una entità è anche l'ID di un'altra entità.

        In questo esempio, la classe Address ha un campo id, ma non è annotato con @GeneratedValue,
        il che significa che l'ID non verrà generato automaticamente al momento della persistenza.
        Tuttavia, l'annotazione @MapsId viene utilizzata sopra la relazione @OneToOne con User,
        indicando che l'ID di Address sarà lo stesso dell'ID di User.

        Quando si salva un'istanza di Address, l'ID di Address sarà impostato sullo stesso valore dell'ID
        dell'istanza di User a cui è associata. Questo può essere utile in situazioni in cui si desidera mantenere
        una relazione diretta tra l'ID di due entità.
     */
    @OneToOne
    @MapsId
    private User user;
   
    //... getters and setters
}



