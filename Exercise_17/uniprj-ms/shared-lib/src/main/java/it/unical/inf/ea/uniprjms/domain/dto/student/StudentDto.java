package it.unical.inf.ea.uniprjms.domain.dto.student;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "{firstname.notempty}")
    private String firstName;

    private String email;

    private double examAverageGrade;

    private LocalDate birthDate;

    private Gender gender;

    private boolean wantsNewsletter;

    private String addressStreet; // address è il nome della variabile che fa riferimento ad Address al quale aggiungo il nome della variabile a cui voglio accedere

    private String addressNumber;

    private String addressCity;

}
