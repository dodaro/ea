package main;

import reflection.JSONUtil;

public class Main {

    public static void main(String[] args) {
        Person p = new Person("Mario", "Rossi", 20);
        try {
            System.out.println(JSONUtil.toJSON(p).toString(2));
            System.out.println(JSONUtil.toJSONUsingFields(p).toString(2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
