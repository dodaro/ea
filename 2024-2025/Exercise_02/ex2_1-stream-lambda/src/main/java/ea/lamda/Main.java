package ea.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main
{
    static String x = "World";
    public static void main(final String[] argv)
    {
        ex1();
        ex2();

        BiFunction<Integer, Integer, Double> add = (a, b) -> (double) a + b;
        System.out.println(add.apply(3,4 ));

        final BiFunction<String, String, String> concat = (final String s1, final String s2) ->
        {
            return s1 + "-" + s2;
        };
        // final BiFunction<String, String, String> concat2 = (s1, s2) -> s1 + "-" + s2;

        System.out.println(concat.apply("Ciccio", "Pasticcio"));

        //
        final Supplier<StringBuilder> s1 = StringBuilder::new;
        final Supplier<StringBuilder> s2 = () -> new StringBuilder();

    }

    private static void ex1() {
        final String x = "World (2)";

        final Function<String, String> func1 = y ->
            {
                return y + " " + x;
            };
        System.out.println(func1.apply("Hello"));
    }

    private static void ex2()
    {
        final List<String> list = Arrays.asList("ciao", "che", "fai", "di", "bello");
        for (final String s : list)
        {
            System.out.print(s+ " ");
        }
        System.out.println();

        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.print(s + " ");
            }
        });
        System.out.println();
        list.stream().forEach(s -> System.out.print(s+ " "));
        System.out.println();
        list.stream().forEach(System.out:: print);
        System.out.println();

        Stream<Double> stream = Stream.generate(()-> new Random().nextDouble(100)+ 1);
        Stream<Integer> stream2 = Stream.iterate(2, n -> n + 2);

        // TODO usa con limit
        //stream/*.limit(10)*/.forEach(System.out::println);
        //stream2/*.limit(10)*/.forEach(System.out::println);
    }
}
