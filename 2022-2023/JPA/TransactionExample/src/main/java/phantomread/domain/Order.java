package phantomread.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private double price;

    public Order() {}

    public void setPrice(double price) {
        this.price = price;
    }

    public Order(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "description='" + description + '\'' +
                ", value=" + price +
                '}';
    }
}

