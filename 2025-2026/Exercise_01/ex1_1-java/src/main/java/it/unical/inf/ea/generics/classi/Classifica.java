package it.unical.inf.ea.generics.classi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Esempio 1 — Classe generica Classifica<T extends Comparable<T>>.
 *
 * Una classifica generica che mantiene elementi ordinati.
 * Il bound "T extends Comparable<T>" è necessario per poter ordinare:
 * senza di esso il compilatore non saprebbe che T supporta compareTo().
 *
 * Funziona con qualsiasi tipo che implementi Comparable:
 *   Classifica<Studente>  → classifica per media voti
 *   Classifica<Prodotto>  → classifica per prezzo
 *   Classifica<Integer>   → classifica numerica
 *
 * @param <T> tipo degli elementi — deve implementare Comparable con se stesso
 */
public class Classifica<T extends Comparable<T>> {

    private final String nome;
    private final List<T> elementi = new ArrayList<>();

    public Classifica(String nome) {
        this.nome = nome;
    }

    /** Aggiunge un elemento e mantiene la lista ordinata (crescente). */
    public void aggiungi(T elemento) {
        elementi.add(elemento);
        Collections.sort(elementi); // possibile grazie al bound: T ha compareTo()
    }

    /**
     * Restituisce il primo classificato (elemento minore).
     * Es. studente con media più bassa, prodotto meno costoso.
     */
    public T primo() {
        if (elementi.isEmpty()) throw new IllegalStateException("Classifica vuota");
        return elementi.get(0);
    }

    /**
     * Restituisce l'ultimo classificato (elemento maggiore).
     * Es. studente con media più alta, prodotto più costoso.
     */
    public T ultimo() {
        if (elementi.isEmpty()) throw new IllegalStateException("Classifica vuota");
        return elementi.get(elementi.size() - 1);
    }

    /** Restituisce l'elemento alla posizione (1-based). */
    public T posizione(int pos) {
        if (pos < 1 || pos > elementi.size())
            throw new IndexOutOfBoundsException("Posizione non valida: " + pos);
        return elementi.get(pos - 1);
    }

    public int size() { return elementi.size(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Classifica [" + nome + "]\n");
        for (int i = 0; i < elementi.size(); i++) {
            sb.append(String.format("  %d. %s%n", i + 1, elementi.get(i)));
        }
        return sb.toString();
    }
}
