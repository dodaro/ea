package it.unical.inf.ea.generics.pecs;


import it.unical.inf.ea.generics.model.Prodotto;
import it.unical.inf.ea.generics.model.Studente;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo PECS completo con Studente e Prodotto.
 *
 * Esegui con:
 *   java -cp out it.unical.inf.ea.generics.pecs.PecsDemo
 */
public class PecsDemo {

    public static void main(String[] args) {

        // ════════════════════════════════════════════════════════════════════
        // PARTE 1 — PECS con Studente
        // ════════════════════════════════════════════════════════════════════

        System.out.println("══════════════════════════════════════");
        System.out.println(" PECS con Studente");
        System.out.println("══════════════════════════════════════");

        List<Studente> gruppo1 = List.of(
            new Studente("221001", "Lucia Greco",  30.0),
            new Studente("223456", "Mario Rossi",  27.5),
            new Studente("224789", "Paolo Bruno",  24.0)
        );

        List<Studente> gruppo2 = List.of(
            new Studente("220033", "Anna Marino",  28.5),
            new Studente("225512", "Marco Foti",   19.0)
        );

        // PRODUCER EXTENDS: calcolaMediaVoti legge da entrambe le liste
        System.out.printf("Media gruppo 1: %.2f%n",
                Segreteria.calcolaMediaVoti(gruppo1));   // 27.17
        System.out.printf("Media gruppo 2: %.2f%n",
                Segreteria.calcolaMediaVoti(gruppo2));   // 23.75

        // CONSUMER SUPER: consolidiamo i gruppi nel registro
        List<Studente> registro = new ArrayList<>();
        Segreteria.aggiungiAlRegistro(gruppo1, registro);
        Segreteria.aggiungiAlRegistro(gruppo2, registro);

        System.out.println("\nRegistro completo (" + registro.size() + " studenti):");
        registro.forEach(s -> System.out.println("  " + s));

        System.out.printf("Media generale: %.2f%n",
                Segreteria.calcolaMediaVoti(registro));  // 25.80

        // ════════════════════════════════════════════════════════════════════
        // PARTE 2 — PECS con Prodotto
        // ════════════════════════════════════════════════════════════════════

        System.out.println("\n══════════════════════════════════════");
        System.out.println(" PECS con Prodotto");
        System.out.println("══════════════════════════════════════");

        List<Prodotto> catalogo = List.of(
            new Prodotto("Caffè",          1.10),
            new Prodotto("Cornetto",       1.50),
            new Prodotto("Acqua",          0.50),
            new Prodotto("Panino",         3.50),
            new Prodotto("Succo",          2.00),
            new Prodotto("Calcolatrice",  12.99)
        );

        // PRODUCER EXTENDS: leggiamo i prezzi
        System.out.printf("Totale catalogo: %.2f€%n",
                Carrello.calcolaTotale(catalogo));  // 21.59€

        // CONSUMER SUPER: filtriamo nel carrello solo prodotti <= 2€
        List<Prodotto> carrello = new ArrayList<>();
        Carrello.aggiungiSeEconomico(catalogo, carrello, 2.00);

        System.out.println("\nCarrello (prodotti ≤ 2.00€):");
        carrello.forEach(p -> System.out.println("  " + p));

        System.out.printf("Totale carrello: %.2f€%n",
                Carrello.calcolaTotale(carrello));  // 5.10€
    }
}
