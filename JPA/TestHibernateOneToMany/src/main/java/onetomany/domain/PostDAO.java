package onetomany.domain;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class PostDAO extends DomainObjectDAO {

    public List<Post> findAll() {
        return executeInsideTransaction(
                session -> session.createQuery("from Post", Post.class).list()
        );
    }

    public List<Post> findAllUsingCriteria() {
        return executeInsideTransaction(
            session -> {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Post> query = builder.createQuery(Post.class);
                return session.createQuery(query.select(query.from(Post.class))).list();
            }
        );
    }

    public List<Post> findAllWithComments() {
        return executeInsideTransaction(
        session -> session.createQuery("from Post p join fetch p.comments", Post.class).list()
        );
    }

    public List<Post> findAllWithCommentsUsingCriteria() {
        return executeInsideTransaction(
                session -> {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<Post> query = builder.createQuery(Post.class);
                    Root<Post> postRoot = query.from(Post.class);
                    postRoot.fetch("comments");
                    return session.createQuery(query.select(postRoot)).list();
                }
        );
    }
}
