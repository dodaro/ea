package it.unical.inf.ea.lombok.ex4;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
//@ToString(includeFieldNames = false)
//@ToString(doNotUseGetters = true)
@NoArgsConstructor
@Getter @Setter
public class Account {

    private String id;

    private String name;

    @ToString.Exclude
    private String password;

    // standard getters and setters
}