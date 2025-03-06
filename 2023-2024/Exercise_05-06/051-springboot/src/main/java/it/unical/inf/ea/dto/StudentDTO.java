package it.unical.inf.ea.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentDTO {

    private Integer id;
    private String firstName;
    private String lastName;
}