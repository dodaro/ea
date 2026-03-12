package ea.stream;

import java.util.List;
import java.util.stream.Collectors;


public class ExampleCompareMethods
{
    public static String namesToString7(final List<Product> products)
    {
        final String label = "Names: ";
        final StringBuilder sb = new StringBuilder(label);
        for (final Product p : products)
        {
            if (sb.length() > label.length())
            {
                sb.append(", ");
            }
            sb.append(p.getName());
        }
        sb.append(".");
        return sb.toString();
    }

    public static String namesToString8(final List<Product> products)
    {
        return products.stream() // Convert collection to Stream
                .map(Product::getName) // Map Person to name
                .collect(Collectors.joining(", ", "Names: ", ".")); // Join names
    }

}
