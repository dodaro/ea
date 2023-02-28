package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexObject {

    private Map<String, Integer> map = new HashMap<>();
    private List<Person> people = new ArrayList<>();
    private Float aFloat;

    public ComplexObject(Map<String, Integer> map, List<Person> people, Float aFloat) {
        this.map = map;
        this.people = people;
        this.aFloat = aFloat;
    }
}
