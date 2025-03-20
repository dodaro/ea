package ea.stream;

import java.util.ArrayList;
import java.util.List;

public class Stream4Reduce
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
        // This is more compact approach for filtering data
        final Float totalPrice = productsList.stream()
                .map(product -> product.getPrice())
                .reduce(1.0f, (sum, price) -> sum + price); // accumulating price
        System.out.println(totalPrice);
        // More precise code
        final float totalPrice2 = productsList.stream()
                .map(product -> product.getPrice())
                .reduce(0.0f, Float::sum); // accumulating price, by referring method of Float class
        System.out.println(totalPrice2);

    }
}
