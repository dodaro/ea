package phantomread.domain;

import phantomread.main.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DomainObjectDAO {

    public void insert(Order o) {
        cudOperations(o, OperationType.INSERT);
    }

    public void remove(Order o) {
        cudOperations(o, OperationType.DELETE);
    }

    public void update(Order o) {
        cudOperations(o, OperationType.UPDATE);
    }

    public List<Order> findAll() {
        Transaction transaction = null;
        Session session = null;
        List<Order> orders = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            orders = session.createQuery("from Order ", Order.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            if(session != null)
                session.close();
        }
        return orders;
    }

    public void phantomReadExample() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List<Order> orders = session.createQuery("from Order o where o.price < 1000", Order.class).list();
            Thread.sleep(2000);
            List<Order> orders2 = session.createQuery("from Order o where o.price < 1000", Order.class).list();
            System.out.println(orders.size() + " " + orders2.size());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }
}
