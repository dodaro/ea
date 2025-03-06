package ea.stream;

import lombok.Data;

@Data
class Product
{
    private int id;
    private String name;
    private float price;
    public Product(final int id, final String name, final float price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
