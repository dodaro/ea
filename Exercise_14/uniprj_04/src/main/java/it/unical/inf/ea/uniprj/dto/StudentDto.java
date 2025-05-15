package it.unical.inf.ea.uniprj.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class StudentDto {

    private Long id;

    @NotBlank
    private String lastName;

    @NotNull(message = "{firstname.notempty}")
    private String firstName;

    @Email
    private String email;

    @Min(value = 18, message = "{examAverageGrade.min}")
    @Max(value = 30, message = "{examAverageGrade.max}")
    private double examAverageGrade;

    @Past
    private LocalDate birthDate;

    private Gender gender;

    private boolean wantsNewsletter;

    private String addressStreet; // address è il nome della variabile che fa riferimento ad Address al quale aggiungo il nome della variabile a cui voglio accedere

    private String addressNumber;

    private String addressCity;

}
