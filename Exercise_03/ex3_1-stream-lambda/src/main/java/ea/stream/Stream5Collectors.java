package ea.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream5Collectors
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
        // Using Collectors's method to sum the prices.
        final double totalPrice3 = productsList.stream().collect(Collectors.summingDouble(product -> product.getPrice()));
        System.out.println(totalPrice3);

    }
}
