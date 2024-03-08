package main;

import reflection.JSONUtil;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Person p1 = new Person("Mario", "Rossi", 20);
        Person p2 = new Person("Simona", "Bianchi", 25);
        ComplexObject object = new ComplexObject(Map.of("value1", 1, "value2", 5, "value3", 3), List.of(p1, p2), 5.2f);
        try {
            System.out.println(JSONUtil.toJSON(p1).toString(2));
            System.out.println(JSONUtil.toJSONUsingFields(p2).toString(2));
            System.out.println(JSONUtil.toJSONUsingFields(object).toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
