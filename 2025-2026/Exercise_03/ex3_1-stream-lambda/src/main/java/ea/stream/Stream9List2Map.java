package ea.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream9List2Map
{
    public static void main(final String[] args)
    {
        final List<Product> productsList = new ArrayList<Product>();

        //Adding Products
        productsList.add(new Product(1, "HP Laptop", 25000f));
        productsList.add(new Product(2, "Dell Laptop", 30000f));
        productsList.add(new Product(3, "Lenovo Laptop", 28000f));
        productsList.add(new Product(4, "Sony Laptop", 28000f));
        productsList.add(new Product(5, "Apple Laptop", 90000f));

        final Map<Integer, String> basket = new HashMap<>();
        for (final Product product : productsList)
        {
            if (product.getPrice() > 30000)
            {
                basket.put(product.getId(), product.getName());
            }
        }

        // Converting Product List into a Map
        final Map<Integer, String> productPriceMap = productsList
                .stream()
                .filter(product -> product.getPrice() > 30000)
                .collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));

        System.out.println(productPriceMap);
    }
}
