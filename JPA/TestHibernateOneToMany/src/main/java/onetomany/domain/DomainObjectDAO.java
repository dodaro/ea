package onetomany.domain;

import onetomany.main.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

public class DomainObjectDAO {

    public void insert(Object o) {
        executeInsideCUDTransaction(session -> session.persist(o));
    }

    public void delete(Object o) {
        executeInsideCUDTransaction(session -> session.remove(o));
    }

    public void update(Object o) {
        executeInsideCUDTransaction(session -> session.merge(o));
    }

    // Consumer can be used to execute a function without return value.
    protected void executeInsideCUDTransaction(Consumer<Session> action) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Function represents a function which takes a parameter and returns a value.
    protected <T> T executeInsideTransaction(Function<Session, T> action) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            T result = action.apply(session);

            transaction.commit();
            return result;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
