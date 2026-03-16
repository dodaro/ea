package it.unical.inf.ea.generics.classi;


import it.unical.inf.ea.generics.model.Prodotto;
import it.unical.inf.ea.generics.model.Studente;

/**
 * Demo di Classifica<T extends Comparable<T>>.
 *
 * La stessa classe Classifica lavora con Studente, Prodotto e Integer
 * senza mai usare cast o duplicare il codice.
 */
public class ClassificaDemo {

    public static void main(String[] args) {

        // ── Classifica<Studente> — ordinata per media voti ───────────────────
        System.out.println("=== Classifica<Studente> ===");

        Classifica<Studente> classeVoti = new Classifica<>("Esame Enterprise Applications");
        classeVoti.aggiungi(new Studente("223456", "Mario Rossi",  27.5));
        classeVoti.aggiungi(new Studente("221001", "Lucia Greco",  30.0));
        classeVoti.aggiungi(new Studente("224789", "Paolo Bruno",  24.0));
        classeVoti.aggiungi(new Studente("220033", "Anna Marino",  28.5));
        classeVoti.aggiungi(new Studente("225512", "Marco Foti",   19.0));

        System.out.print(classeVoti);
        System.out.println("Ultimo in classifica: " + classeVoti.primo());   // media 19.0
        System.out.println("Primo in classifica:  " + classeVoti.ultimo());  // media 30.0
        System.out.println();

        // ── Classifica<Prodotto> — ordinata per prezzo ───────────────────────
        System.out.println("=== Classifica<Prodotto> ===");

        Classifica<Prodotto> prezzi = new Classifica<>("Prezzi bar universitario");
        prezzi.aggiungi(new Prodotto("Caffè",    1.10));
        prezzi.aggiungi(new Prodotto("Cornetto", 1.50));
        prezzi.aggiungi(new Prodotto("Acqua",    0.50));
        prezzi.aggiungi(new Prodotto("Panino",   3.50));
        prezzi.aggiungi(new Prodotto("Succo",    2.00));

        System.out.print(prezzi);
        System.out.println("Più economico: " + prezzi.primo());   // Acqua 0.50
        System.out.println("Più costoso:   " + prezzi.ultimo());  // Panino 3.50
        System.out.println("3° posto:      " + prezzi.posizione(3));
        System.out.println();

        // ── Classifica<Integer> — funziona anche con tipi primitivi boxed ────
        System.out.println("=== Classifica<Integer> ===");

        Classifica<Integer> punteggi = new Classifica<>("Punteggi quiz");
        punteggi.aggiungi(45);
        punteggi.aggiungi(78);
        punteggi.aggiungi(62);
        punteggi.aggiungi(91);
        punteggi.aggiungi(55);

        System.out.print(punteggi);
        System.out.println("Punteggio minimo: " + punteggi.primo());   // 45
        System.out.println("Punteggio massimo: " + punteggi.ultimo()); // 91
    }
}
