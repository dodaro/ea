package it.unical.inf.ea.lombok.ex1;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {
        PersonLombok p = new PersonLombok("ciccio", "pasticcio", LocalDate.now());

        p.setFirstName("ciccio");
        p.getLastName();
    }
}
