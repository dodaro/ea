package it.unical.inf.ea.generics.model;

public class Studente implements Comparable<Studente> {

    private final String matricola;
    private final String nome;
    private final double media;

    public Studente(String matricola, String nome, double media) {
        this.matricola = matricola;
        this.nome      = nome;
        this.media     = media;
    }

    public String getMatricola() { return matricola; }
    public String getNome()      { return nome; }
    public double getMedia()     { return media; }

    @Override
    public int compareTo(Studente altro) {
        return Double.compare(this.media, altro.media);
    }

    @Override
    public String toString() {
        return String.format("Studente{matricola='%s', nome='%s', media=%.1f}",
                             matricola, nome, media);
    }
}
