package it.unical.inf.ea.lombok.ex5;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PersonLombok {
	String firstName;
	String lastName;
	LocalDate dateOfBirth;
}