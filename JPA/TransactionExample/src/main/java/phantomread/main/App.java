package phantomread.main;

import phantomread.domain.Order;
import phantomread.domain.OrderDAO;

public class App {

    public static void main(String[] args) throws InterruptedException {
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order("order1", 10.0);
        orderDAO.insert(order);
        Thread t1 = new Thread(() -> orderDAO.phantomReadExample());
        t1.start();
        Thread.sleep(500);
        Thread t2 = new Thread(() -> { orderDAO.insert(new Order("order2", 400.0)); });
        t2.start();
        t1.join();
        t2.join();
        System.out.println(orderDAO.findAll());
    }
}
