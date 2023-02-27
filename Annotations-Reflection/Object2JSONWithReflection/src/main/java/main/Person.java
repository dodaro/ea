package main;

import reflection.Ignore;

public class Person {

    private String firstName;
    private String lastName;
    private int age;
    @Ignore
    private String toIgnore;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.toIgnore = "Hi, this will be ignored!";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
