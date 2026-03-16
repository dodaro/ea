package ea.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Product
{
    private int id;
    private String name;
    private float price;
}
