package it.unical.inf.ea.uniprj.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class StudentDto {

    private Long id;

    private String lastName;

    private String firstName;

    private LocalDate birthDate;

    private Gender gender;

    private boolean wantsNewsletter;

    private String addressStreet; // address è il nome della variabile che fa riferimento ad Address al quale aggiungo il nome della variabile a cui voglio accedere

    private String addressNumber;

    private String addressCity;

}
