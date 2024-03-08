package onetomany.domain;

import onetomany.main.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends DomainObjectDAO {
    public List<Comment> findAll() {
        Transaction transaction = null;
        List<Comment> comments = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            comments = session.createQuery("from Comment ", Comment.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return comments;
    }

    public void delete(Comment c) { cudOperations(c, OperationType.DELETE); }

    public void update(Comment c) {
        cudOperations(c, OperationType.UPDATE);
    }
}
