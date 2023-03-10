package locking.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private double price;

    @Version
    private long changeVersion;

    public Order() {}

    public Order(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public void setPrice(double value) {
        this.price = value;
    }

    @Override
    public String toString() {
        return "Order{" +
                "description='" + description + '\'' +
                ", value=" + price +
                '}';
    }
}

