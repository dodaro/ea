package ea.stream;

import java.util.stream.Stream;

public class Stream2Iterating
{
    public static void main(final String[] args)
    {
        Stream.iterate(1, element -> element + 2)
                .filter(element -> element % 5 == 0)
                .limit(5).forEach(System.out::println);
    }
}
