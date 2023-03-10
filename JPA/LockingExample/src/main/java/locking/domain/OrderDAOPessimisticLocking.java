package locking.domain;

import jakarta.persistence.LockModeType;
import locking.main.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OrderDAOPessimisticLocking extends DomainObjectDAO {

    public void insert(Order o) {
        cudOperations(o, OperationType.INSERT);
    }

    public void delete(Order o) {
        cudOperations(o, OperationType.DELETE);
    }

    public void update(Order o) {
        cudOperations(o, OperationType.UPDATE);
    }

    public List<Order> findAll() {
        Transaction transaction = null;
        List<Order> orders = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            orders = session.createQuery("from Order", Order.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return orders;
    }

    public void longUpdate(double price) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Order> query = session.createQuery("from Order", Order.class);
            //None can modify this data
            query.setLockMode(LockModeType.PESSIMISTIC_READ);
            //None can read or modify this data
            //query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
            List<Order> orders = query.list();
            Thread.sleep(2000);
            Order o = orders.get(0);
            o.setPrice(price);
            session.merge(o);
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Exception durante l'aggiornamento di price");
            if (transaction != null) {
                transaction.rollback();
            }
        }
        finally {
            if(session != null)
                session.close();
        }
    }
}
