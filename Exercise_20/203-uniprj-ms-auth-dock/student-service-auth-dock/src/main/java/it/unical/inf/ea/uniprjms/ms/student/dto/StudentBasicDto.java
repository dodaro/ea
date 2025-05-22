package it.unical.inf.ea.uniprjms.ms.student.dto;

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

    private String email;

    private double examAverageGrade;

    private LocalDate birthDate;

    private Gender gender;

    private boolean wantsNewsletter;

}
