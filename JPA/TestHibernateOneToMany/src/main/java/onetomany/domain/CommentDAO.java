package onetomany.domain;

import java.util.List;

public class CommentDAO extends DomainObjectDAO {
    public List<Comment> findAll() {
        return executeInsideTransaction(
                session -> session.createQuery("from Comment", Comment.class).list()
        );
    }
}
