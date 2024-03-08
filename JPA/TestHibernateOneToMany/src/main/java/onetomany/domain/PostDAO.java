package onetomany.domain;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import onetomany.main.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PostDAO extends DomainObjectDAO {

    public void insert(Post p) {
        cudOperations(p, OperationType.INSERT);
    }

    public void delete(Post p) {
        cudOperations(p, OperationType.DELETE);
    }

    public void update(Post p) {
        cudOperations(p, OperationType.UPDATE);
    }

    public List<Post> findAll() {
        Transaction transaction = null;
        List<Post> posts = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            posts = session.createQuery("from Post", Post.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return posts;
    }

    public List<Post> findAllUsingCriteria() {
        Transaction transaction = null;
        List<Post> posts = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Post> query = builder.createQuery(Post.class);
            posts = session.createQuery(query.select(query.from(Post.class))).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return posts;
    }

    public List<Post> findAllWithComments() {
        Transaction transaction = null;
        List<Post> posts = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            posts = session.createQuery("from Post p join fetch p.comments", Post.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return posts;
    }

    public List<Post> findAllWithCommentsUsingCriteria() {
        Transaction transaction = null;
        List<Post> posts = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Post> query = builder.createQuery(Post.class);
            Root<Post> postRoot = query.from(Post.class);
            postRoot.fetch("comments");
            posts = session.createQuery(query.select(postRoot)).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return posts;
    }
}
