package it.unical.inf.ea.generics.pecs;


import it.unical.inf.ea.generics.model.Prodotto;

import java.util.List;

/**
 * Esempio PECS con Prodotto.
 *
 * PECS — Producer Extends, Consumer Super:
 *   List<? extends Prodotto>  → leggiamo dalla lista  (Producer)
 *   List<? super   Prodotto>  → scriviamo nella lista (Consumer)
 */
public class Carrello {

    /**
     * Calcola il totale dei prezzi.
     *
     * PRODUCER EXTENDS: leggiamo i prezzi, non modifichiamo la lista.
     * Accetta List<Prodotto> e qualsiasi sua sottoclasse futura.
     *
     * @param prodotti lista da cui leggere
     * @return somma dei prezzi
     */
    public static double calcolaTotale(List<? extends Prodotto> prodotti) {
        return prodotti.stream()
                       .mapToDouble(Prodotto::getPrezzo)
                       .sum();

        // prodotti.add(new Prodotto(...));  // ✗ ERRORE compile: extends = solo lettura
    }

    /**
     * Aggiunge al carrello solo i prodotti con prezzo <= prezzoMax.
     *
     * src:      PRODUCER EXTENDS — leggiamo i prodotti da src
     * carrello: CONSUMER SUPER   — scriviamo i prodotti selezionati in carrello
     *
     * @param src       catalogo sorgente
     * @param carrello  lista destinazione
     * @param prezzoMax soglia massima di prezzo (inclusa)
     */
    public static void aggiungiSeEconomico(List<? extends Prodotto> src,
                                           List<? super   Prodotto> carrello,
                                           double prezzoMax) {
        for (Prodotto p : src) {
            if (p.getPrezzo() <= prezzoMax) {
                carrello.add(p);  // OK: super garantisce che carrello accetti Prodotto
            }
        }
        // Prodotto x = carrello.get(0);  // ✗ ERRORE compile: super = get() torna Object
    }
}
