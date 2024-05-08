package it.unical.inf.ea.lombok.ex1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class PersonLombok {
	String firstName;
	String lastName;
	LocalDate dateOfBirth;
}