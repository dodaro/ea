package it.unical.inf.ea.lombok.ex4;

import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
public class Account2 {

    @ToString.Include(rank = 1)
    private String id;

    private String name;

    private String password;

    // standard getters and setters
}