package it.unical.inf.ea.generics.model;

public class Prodotto implements Comparable<Prodotto> {

    private final String nome;
    private final double prezzo;

    public Prodotto(String nome, double prezzo) {
        this.nome   = nome;
        this.prezzo = prezzo;
    }

    public String getNome()   { return nome; }
    public double getPrezzo() { return prezzo; }

    @Override
    public int compareTo(Prodotto altro) {
        return Double.compare(this.prezzo, altro.prezzo);
    }

    @Override
    public String toString() {
        return String.format("Prodotto{nome='%s', prezzo=%.2f}", nome, prezzo);
    }
}
