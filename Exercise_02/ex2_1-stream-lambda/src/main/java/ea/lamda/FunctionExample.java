package ea.lamda;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.LongToIntFunction;

public class FunctionExample
{
    public static <T, U, V> Function<U, V> applyPartial(final BiFunction<T, U, V> bif, final T firstArgument)
    {
        return u -> bif.apply(firstArgument, u);
    }

    public static void main(final String[] args)
    {
        final BiFunction<Integer, Integer, Integer> sumBiFunction = (a, b) -> a + b;
        Function<Integer, Integer> partial = applyPartial(sumBiFunction, 5);
        sumNumbers(partial);

        //final LongToIntFunction i = (l) -> Math.toIntExact(l);

        //System.out.println(i.applyAsInt(Long.MAX_VALUE));

    }

    public static void sumNumbers(final Function<Integer, Integer> adder)
    {
        for (final Integer n : Arrays.asList(3, 8, 10))
        {
            System.out.println(adder.apply(n));
        }
    }
}
