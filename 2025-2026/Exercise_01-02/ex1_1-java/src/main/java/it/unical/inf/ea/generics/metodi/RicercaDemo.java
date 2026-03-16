package it.unical.inf.ea.generics.metodi;

import it.unical.inf.ea.generics.model.Prodotto;
import it.unical.inf.ea.generics.model.Studente;

import java.util.List;

/**
 * Demo di metodi generici statici con bound applicati a entità reali.
 *
 * Gli stessi metodi ricercaBinaria(), minMax() e contaInRange()
 * vengono usati prima con Integer, poi con Prodotto, poi con Studente —
 * senza cambiare una riga di codice in RicercaUtils.
 */
public class RicercaDemo {

    public static void main(String[] args) {

        // ── ricercaBinaria su Integer ────────────────────────────────────────
        System.out.println("=== ricercaBinaria() su Integer ===");

        List<Integer> numeri = List.of(2, 5, 8, 12, 16, 23, 38, 45, 67, 90);
        System.out.println("Lista: " + numeri);

        int idx = RicercaUtils.ricercaBinaria(numeri, 23);
        System.out.println("Ricerca 23 → indice: " + idx);        // 5

        idx = RicercaUtils.ricercaBinaria(numeri, 10);
        System.out.println("Ricerca 10 → indice: " + idx);        // -1 (non presente)
        System.out.println();

        // ── minMax() su Prodotto ─────────────────────────────────────────────
        System.out.println("=== minMax() su Prodotto (confronto per prezzo) ===");

        List<Prodotto> catalogo = List.of(
            new Prodotto("Penna",      0.80),
            new Prodotto("Quaderno",   2.50),
            new Prodotto("Zaino",     45.00),
            new Prodotto("Calcolatrice", 12.99),
            new Prodotto("Evidenziatore", 1.20)
        );

        Prodotto[] estremi = RicercaUtils.minMax(catalogo);
        System.out.println("Prodotto meno costoso: " + estremi[0]);  // Penna 0.80
        System.out.println("Prodotto più costoso:  " + estremi[1]);  // Zaino 45.00
        System.out.println();

        // ── contaInRange() su Prodotto ───────────────────────────────────────
        System.out.println("=== contaInRange() su Prodotto ===");

        Prodotto minPrezzo = new Prodotto("min", 1.00);
        Prodotto maxPrezzo = new Prodotto("max", 15.00);
        int count = RicercaUtils.contaInRange(catalogo, minPrezzo, maxPrezzo);
        System.out.println("Prodotti tra 1.00€ e 15.00€: " + count);  // 3
        System.out.println();

        // ── minMax() su Studente ─────────────────────────────────────────────
        System.out.println("=== minMax() su Studente (confronto per media) ===");

        List<Studente> studenti = List.of(
            new Studente("221001", "Lucia Greco",  30.0),
            new Studente("223456", "Mario Rossi",  27.5),
            new Studente("224789", "Paolo Bruno",  24.0),
            new Studente("220033", "Anna Marino",  28.5),
            new Studente("225512", "Marco Foti",   19.0)
        );

        Studente[] miglioreEPeggiore = RicercaUtils.minMax(studenti);
        System.out.println("Media più bassa: " + miglioreEPeggiore[0]);  // Marco 19.0
        System.out.println("Media più alta:  " + miglioreEPeggiore[1]);  // Lucia 30.0
        System.out.println();

        // ── contaInRange() su Studente ───────────────────────────────────────
        System.out.println("=== contaInRange() su Studente ===");

        Studente sogliaBassa = new Studente("", "", 24.0);
        Studente sogliaAlta  = new Studente("", "", 28.5);
        int nStudenti = RicercaUtils.contaInRange(studenti, sogliaBassa, sogliaAlta);
        System.out.println("Studenti con media tra 24.0 e 28.5: " + nStudenti);  // 3
    }
}
