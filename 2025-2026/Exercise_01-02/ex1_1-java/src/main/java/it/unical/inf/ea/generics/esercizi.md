# Esercizi — Java Generics: PECS
**Corso:** Enterprise Applications · Università della Calabria  
**Argomento:** Producer Extends, Consumer Super  
**Livello:** ★★☆ / ★★★

---

## Esercizio 1 — Biblioteca universitaria ★★☆

### Contesto

Una biblioteca universitaria gestisce libri di testo e riviste scientifiche.
Entrambi i tipi estendono `Pubblicazione`, che implementa `Comparable<Pubblicazione>` per prezzo.

### Entità da creare

```java
public class Pubblicazione implements Comparable<Pubblicazione> {
    private String titolo;
    private double prezzo;

    public Pubblicazione(String titolo, double prezzo) { ... }

    public String getTitolo()  { return titolo; }
    public double getPrezzo()  { return prezzo; }

    @Override
    public int compareTo(Pubblicazione altra) {
        return Double.compare(this.prezzo, altra.prezzo);
    }

    @Override
    public String toString() { ... }
}

public class LibroTesto extends Pubblicazione {
    private String materia;
    public LibroTesto(String titolo, double prezzo, String materia) { ... }
}

public class RivistaScientifica extends Pubblicazione {
    private String issn;
    public RivistaScientifica(String titolo, double prezzo, String issn) { ... }
}
```

### Classe `Biblioteca` da completare

```java
public class Biblioteca {

    /**
     * TODO 1 — PRODUCER EXTENDS
     *
     * Calcola il prezzo totale di una lista di pubblicazioni.
     * Deve accettare List<Pubblicazione>, List<LibroTesto>, List<RivistaScientifica>.
     * La lista NON deve essere modificata.
     */
    public static double calcolaTotale(/* ??? */ pubblicazioni) {
        // ...
    }

    /**
     * TODO 2 — PRODUCER EXTENDS + CONSUMER SUPER
     *
     * Sposta in "archivio" solo le pubblicazioni con prezzo > sogliaPrezzo.
     * Leggi da "sorgente", scrivi in "archivio".
     */
    public static void archivia(/* ??? */ sorgente,
                                /* ??? */ archivio,
                                double sogliaPrezzo) {
        // ...
    }
}
```

### Output atteso

```java
List<LibroTesto> libri = List.of(
    new LibroTesto("Algoritmi", 45.00, "Informatica"),
    new LibroTesto("Analisi I", 38.00, "Matematica"),
    new LibroTesto("Fisica",    22.00, "Fisica")
);

List<RivistaScientifica> riviste = List.of(
    new RivistaScientifica("IEEE Software", 120.00, "1234-5678"),
    new RivistaScientifica("Nature",         95.00, "8765-4321")
);

System.out.println(calcolaTotale(libri));    // 105.0
System.out.println(calcolaTotale(riviste));  // 215.0

List<Pubblicazione> archivio = new ArrayList<>();
archivia(libri,   archivio, 30.0);   // archivia Algoritmi (45€) e Analisi I (38€)
archivia(riviste, archivio, 30.0);   // archivia entrambe le riviste
System.out.println(archivio.size()); // 4
```

### Domande di riflessione

1. Perché `calcolaTotale` deve usare `? extends` e non `? super`?
2. Prova a passare `archivio` (tipo `List<Pubblicazione>`) come `sorgente` di `archivia()` — compila? Perché?
3. Prova a dichiarare `archivio` come `List<Object>` invece di `List<Pubblicazione>` — funziona ancora? Cosa cambia nell'usabilità dopo?

---

## Esercizio 2 — Sportello valutazioni ★★★

### Contesto

Uno sportello universitario confronta studenti di corsi diversi e produce report sulle idoneità.
Partendo dalla classe `Studente` già vista, si aggiungono due sottoclassi con informazioni specifiche.

### Entità da creare

```java
public class StudenteTriennale extends Studente {
    private String corsoDiLaurea;

    public StudenteTriennale(String matricola, String nome,
                             double media, String corsoDiLaurea) { ... }
}

public class StudenteMagistrale extends Studente {
    private String curriculum;

    public StudenteMagistrale(String matricola, String nome,
                              double media, String curriculum) { ... }
}
```

> `Studente` ha già `implements Comparable<Studente>` con confronto per media.

### Classe `Sportello` da completare

```java
public class Sportello {

    /**
     * TODO 1 — PRODUCER EXTENDS
     *
     * Restituisce lo studente con la media più alta.
     * Deve funzionare con List<Studente>, List<StudenteTriennale>,
     * List<StudenteMagistrale> senza modificare la lista.
     */
    public static Studente migliore(/* ??? */ studenti) {
        // suggerimento: usa compareTo() già definito in Studente
    }

    /**
     * TODO 2 — PRODUCER EXTENDS + CONSUMER SUPER
     *
     * Unisce due liste in una lista destinazione,
     * includendo solo gli studenti con media >= sogliaMinima.
     * Entrambe le liste sorgente non devono essere modificate.
     */
    public static void unisciPromossi(/* ??? */ gruppo1,
                                      /* ??? */ gruppo2,
                                      /* ??? */ destinazione,
                                      double sogliaMinima) {
        // ...
    }

    /**
     * TODO 3 — PRODUCER EXTENDS
     *
     * Conta quanti studenti hanno media strettamente superiore
     * alla media dell'intero gruppo.
     * Suggerimento: calcola prima la media del gruppo, poi conta.
     */
    public static int contaSopraMedia(/* ??? */ studenti) {
        // ...
    }
}
```

### Output atteso

```java
List<StudenteTriennale> triennali = List.of(
    new StudenteTriennale("221001", "Lucia",  30.0, "Informatica"),
    new StudenteTriennale("223456", "Mario",  24.0, "Informatica"),
    new StudenteTriennale("224789", "Paolo",  27.5, "Matematica")
);

List<StudenteMagistrale> magistrali = List.of(
    new StudenteMagistrale("220033", "Anna",  28.5, "AI"),
    new StudenteMagistrale("225512", "Marco", 19.0, "Sistemi")
);

// TODO 1
System.out.println(migliore(triennali));   // Lucia (30.0)
System.out.println(migliore(magistrali));  // Anna (28.5)

// TODO 2
List<Studente> idonei = new ArrayList<>();
unisciPromossi(triennali, magistrali, idonei, 25.0);
idonei.forEach(System.out::println);
// Lucia (30.0), Paolo (27.5), Anna (28.5)

// TODO 3
System.out.println(contaSopraMedia(idonei));
// media del gruppo = 28.67 → solo Lucia (30.0) è sopra → 1
```

### Domande di riflessione

1. In `unisciPromossi`, perché `destinazione` usa `? super Studente` e non `? extends Studente`?
2. Potresti dichiarare `idonei` come `List<Object>`? Cosa perderesti in termini di usabilità dopo?
3. `migliore()` restituisce `Studente` anche quando passi una `List<StudenteTriennale>`. Come cambieresti la firma per restituire il tipo esatto (`StudenteTriennale`)? Quali problemi introduce questo cambiamento?

---

## Riepilogo PECS

| Situazione | Wildcard | Puoi fare | Non puoi fare |
|---|---|---|---|
| Leggi dalla lista | `? extends T` | `get()` → restituisce T | `add()` → errore compile |
| Scrivi nella lista | `? super T` | `add(T)` → OK | `get()` → restituisce solo `Object` |
| Entrambe | un parametro `extends` + uno `super` | leggi dall'uno, scrivi nell'altro | — |

> **Regola pratica:** chiediti sempre _"sto leggendo o scrivendo?"_  
> Leggo → `extends`. Scrivo → `super`. Entrambe → un parametro per ciascuno.