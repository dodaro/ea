package it.unical.inf.ea.generics.metodi;

import java.util.List;

/**
 * Esempio 2 — Metodi generici statici con bound: ricerca e confronto.
 *
 * Ogni metodo che confronta elementi dichiara il bound:
 *   <T extends Comparable<T>>
 *
 * Il compilatore inferisce T dagli argomenti — non va scritto esplicitamente.
 * Stesso metodo funziona con Studente, Prodotto, Integer, String...
 */
public class RicercaUtils {

    /**
     * Ricerca binaria generica su una lista già ordinata.
     * Restituisce l'indice dell'elemento cercato, o -1 se non trovato.
     *
     * Il bound è necessario: compareTo() viene chiamato ad ogni iterazione.
     * Funziona con qualsiasi List<T> ordinata: Integer, String, Prodotto...
     *
     * @param lista   lista ordinata in modo crescente
     * @param target  valore da cercare
     * @return indice dell'elemento, o -1 se assente
     */
    public static <T extends Comparable<T>> int ricercaBinaria(List<T> lista, T target) {
        int sinistra = 0;
        int destra   = lista.size() - 1;

        while (sinistra <= destra) {
            int medio = (sinistra + destra) / 2;
            int cmp   = lista.get(medio).compareTo(target);

            if (cmp == 0) return medio;       // trovato
            if (cmp < 0)  sinistra = medio + 1; // target è più grande
            else          destra   = medio - 1; // target è più piccolo
        }
        return -1; // non trovato
    }

    /**
     * Restituisce il minimo e il massimo di una lista in un'unica scansione.
     * Risultato come array di due elementi: [minimo, massimo].
     *
     * @throws IllegalArgumentException se la lista è vuota
     */
    public static <T extends Comparable<T>> T[] minMax(List<T> lista) {
        if (lista.isEmpty())
            throw new IllegalArgumentException("La lista non può essere vuota");

        T min = lista.get(0);
        T max = lista.get(0);

        for (T elemento : lista) {
            if (elemento.compareTo(min) < 0) min = elemento;
            if (elemento.compareTo(max) > 0) max = elemento;
        }

        // SuppressWarnings necessario per la creazione dell'array generico
        @SuppressWarnings("unchecked")
        T[] risultato = (T[]) new Comparable[]{min, max};
        return risultato;
    }

    /**
     * Conta quanti elementi della lista sono compresi tra min e max (inclusi).
     *
     * Es: contaInRange(prezzi, 1.0, 2.5) → prodotti tra 1€ e 2.50€
     */
    public static <T extends Comparable<T>> int contaInRange(List<T> lista, T min, T max) {
        int count = 0;
        for (T elemento : lista) {
            if (elemento.compareTo(min) >= 0 && elemento.compareTo(max) <= 0) {
                count++;
            }
        }
        return count;
    }

    // Costruttore privato: classe di soli metodi statici
    private RicercaUtils() {}
}
