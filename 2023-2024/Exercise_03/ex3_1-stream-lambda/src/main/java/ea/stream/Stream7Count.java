package ea.stream;

import java.util.ArrayList;
import java.util.List;

public class Stream7Count
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
        // count number of products based on the filter
        final long count = productsList.stream().filter(product -> product.getPrice() < 30000).count();
        System.out.println(count);
    }
}
