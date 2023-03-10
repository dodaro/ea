package locking.main;

import locking.domain.DomainObjectDAO;
import locking.domain.Order;
import locking.domain.OrderDAO;
import locking.domain.OrderDAOPessimisticLocking;

public class App {

    public static void example(DomainObjectDAO orderDAO) throws InterruptedException {
        Order order = new Order("order1", 10.0);
        orderDAO.insert(order);
        Thread t1 = new Thread(() -> orderDAO.longUpdate(1000.0));
        t1.start();
        Thread.sleep(500);
        Thread t2 = new Thread(() -> { order.setPrice(500.0); orderDAO.update(order); });
        t2.start();
        t1.join();
        t2.join();
        System.out.println(orderDAO.findAll());
    }

    public static void main(String[] args) throws InterruptedException {
        boolean optimisticLocking = false;
        if(optimisticLocking)
            example(new OrderDAO());
        else
            example(new OrderDAOPessimisticLocking());

    }
}
