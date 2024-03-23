package ea.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream0
{
    public static void main(final String[] args)
    {
        final Collection<Product> products = generateProducts();
        //        products.forEach(System.out::println);

        double average = products.stream() //
                .filter(p -> p.getName().length() < 10) //
                .mapToDouble(Product::getPrice)//
                .average()//
                .getAsDouble();

        average = products.stream().filter(p -> p.getName().length() < 10) //
                .mapToDouble(p -> p.getPrice()).reduce(0, (p1, p2) -> p1 + p2);

        final TreeSet<String> s = products.stream().map(p -> p.getName()).collect(Collectors.toCollection(TreeSet::new));
        final String c = products.stream().map(p -> p.getName()).collect(Collectors.joining("; "));
        final Map<Float, List<Product>> m = products.stream().collect(Collectors.groupingBy(p -> p.getPrice())); //Product::getPrice
        final DoubleSummaryStatistics statistics = products.stream().collect(Collectors.summarizingDouble(Product::getPrice));
        System.out.println(average);

        products.stream().filter(p -> p.getName().length() < 10).collect(Collectors.toList());
        products.stream().map(Product::getName).distinct().collect(Collectors.toList());
        products.stream().filter(p -> p.getName().length() < 10).map(Product::getName).limit(3).collect(Collectors.toList());

        products.stream().map(Product::getName).sorted().collect(Collectors.toList());
        products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
        products.stream().sorted(Comparator.comparing(Product::getName).reversed()).collect(Collectors.toList());
        products.stream().sorted(Comparator.comparing(Product::getName).thenComparing(Product::getPrice))
                .collect(Collectors.toList());

        //true se almeno un oggetto che rispetti la query
        boolean match = products.stream().anyMatch(p -> p.getName().length() < 10);
        //true se almeno tutti gli oggetti rispettano la query
        match = products.stream().allMatch(p -> p.getName().length() < 10);
        //true se nessun oggetto rispetta la query
        match = products.stream().noneMatch(p -> p.getName().length() < 10);

        Optional<Product> res = products.stream().filter(p -> p.getName().length() < 10).findAny();
        res = products.stream().filter(p -> p.getName().length() < 10).findFirst();

        final IntStream mapToInt = products.stream().mapToInt(value -> value.getId());
    }

    private static Collection<Product> generateProducts()
    {
        final Collection<Product> products = new ArrayList<>();
        for (int i = 1; i < 100; i++)
        {

            products.add(new Product(i, "Product_" + i, (float) Math.random() * 100 * i));
        }
        return products;
    }
}
