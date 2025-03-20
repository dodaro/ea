package ea.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream1Filter
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
        final List<Float> productPriceList2 = productsList.stream()//
                .filter((final Product p) -> p.getPrice() > 30000)// filtering data
                .map(p -> p.getPrice()) // fetching price
                .collect(Collectors.toList()); // collecting as list
        System.out.println(productPriceList2);
    }
}
