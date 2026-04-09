package it.unical.inf.ea.jpa.entity.enumeration;

import java.util.stream.Stream;

public enum Priority {
    LOW(100),
    MEDIUM(200, "medium"),
    HIGH(300, "high");
 
    private final int value;

    private final String name;
 
    private Priority(int priority, String name) {
        this.value = priority;
        this.name = name;
    }

    private Priority(int priority) {
        this.value = priority;
        this.name = null;
    }
 
    public int getValue() {
        return value;
    }
 
    public static Priority of(int priority) {
        return Stream.of(Priority.values())
          .filter(p -> p.getValue() == priority)
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}