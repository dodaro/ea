package it.unical.inf.ea.uniprj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class StudentBasicDto {

    private Long id;

    private String lastName;

    private String firstName;

    private LocalDate birthDate;

    private Gender gender;

    private boolean wantsNewsletter;

}
