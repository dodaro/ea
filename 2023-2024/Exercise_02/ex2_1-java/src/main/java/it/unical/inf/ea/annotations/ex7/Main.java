package it.unical.inf.ea.annotations.ex7;



public class Main {
    public static void main(String[] args) {
        // Leggere gli annotazioni sull'utente
        RuoliUtente ruoliUtente = Utente.class.getAnnotation(RuoliUtente.class);
        for (Ruolo ruolo : ruoliUtente.value()) {
            System.out.println("Ruolo: " + ruolo.value());
        }
    }
}