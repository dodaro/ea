package ea.stream;

import java.util.ArrayList;
import java.util.List;

public class Stream6MaxMin
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
        // max() method to get max Product price
        final Product productA = productsList.stream()
                .max((product1, product2) -> product1.getPrice() > product2.getPrice() ? 1 : -1).get();

        System.out.println(productA.getPrice());
        // min() method to get min Product price
        final Product productB = productsList.stream()
                .max((product1, product2) -> product1.getPrice() < product2.getPrice() ? 1 : -1).get();
        System.out.println(productB.getPrice());

    }
}
