package it.unical.inf.ea.generics.pecs;

import it.unical.inf.ea.generics.model.Studente;

import java.util.List;

/**
 * Esempio PECS con Studente.
 *
 * PECS — Producer Extends, Consumer Super:
 *   List<? extends Studente>  → leggiamo dalla lista  (Producer)
 *   List<? super   Studente>  → scriviamo nella lista (Consumer)
 */
public class Segreteria {

    /**
     * Calcola la media dei voti.
     *
     * PRODUCER EXTENDS: la lista produce dati che noi leggiamo.
     * Accetta List<Studente> e qualsiasi sua sottoclasse futura.
     *
     * @param studenti lista da cui leggere (non viene modificata)
     * @return media delle medie, 0.0 se la lista è vuota
     */
    public static double calcolaMediaVoti(List<? extends Studente> studenti) {
        return studenti.stream()
                       .mapToDouble(Studente::getMedia)
                       .average()
                       .orElse(0.0);

        // studenti.add(new Studente(...));  // ✗ ERRORE compile: extends = solo lettura
    }

    /**
     * Aggiunge tutti gli studenti di un gruppo a un registro centralizzato.
     *
     * src:     PRODUCER EXTENDS — leggiamo da src
     * dst:     CONSUMER SUPER   — scriviamo in dst
     *
     * @param src  gruppo sorgente (non modificato)
     * @param dst  registro destinazione (riceve gli studenti)
     */
    public static void aggiungiAlRegistro(List<? extends Studente> src,
                                          List<? super   Studente> dst) {
        for (Studente s : src) {
            dst.add(s);  // OK: super garantisce che dst accetti Studente
        }
        // Studente x = dst.get(0);  // ✗ ERRORE compile: super = get() torna Object
    }
}
