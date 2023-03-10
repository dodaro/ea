package phantomread.domain;

import phantomread.main.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DomainObjectDAO {

    protected enum OperationType {INSERT, DELETE, UPDATE};

    protected void cudOperations(Object o, OperationType operationType) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            switch (operationType) {
                case INSERT -> session.persist(o);
                case DELETE -> session.remove(o);
                case UPDATE -> session.merge(o);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
