package it.unical.inf.ea.lombok.ex3;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Widget {
    private final String name;
    private final int id;

    public static void main(String[] args) {
        Widget testWidget = Widget.builder()
            .name("foo")
            .id(1)
            .build();
    }
}