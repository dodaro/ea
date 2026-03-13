package onetomany.main;

import onetomany.domain.Comment;
import onetomany.domain.CommentDAO;
import onetomany.domain.Post;
import onetomany.domain.PostDAO;

public class App {
    public static void main(String[] args) {
        PostDAO postDAO = new PostDAO();
        CommentDAO commentDAO = new CommentDAO();
        Post post = new Post("ciao a tutti");
        Comment comment1 = new Comment("commento 0");
        Comment comment2 = new Comment("commento 1");
        Comment comment3 = new Comment("commento 2");
        post.addComment(comment1);
        post.addComment(comment2);
        post.addComment(comment3);
        postDAO.insert(post);
        System.out.print("Post content after insertion: ");
        postDAO.findAll().forEach(p -> System.out.println(p.getContent()));
        post = postDAO.findAll().get(0);
        post.setContent("ciao a tutti!");
        postDAO.update(post);
        System.out.print("Post content after update: ");
        postDAO.findAllUsingCriteria().forEach(p -> System.out.println(p.getContent()));
        // postDAO.findAll().forEach(p -> System.out.println(p.getComments())); // Error lazy loading
        System.out.print("Post comments: ");
        postDAO.findAllWithCommentsUsingCriteria().forEach(p -> System.out.println(p.getComments()));
        System.out.println("Removing comment '0' from postDAO");
        post = postDAO.findAllWithComments().get(0);
        post.removeComment(post.getComments().get(0)); // Removed from posts and from comments
        postDAO.update(post);
        System.out.print("\tPost comments after removal: ");
        postDAO.findAllWithComments().forEach(p -> System.out.println(p.getComments()));
        System.out.println("\tComments after removal from post: " + commentDAO.findAll()); // Comment '0' is removed also from comments
        System.out.println("Removing comment '1' from commentDAO");
        commentDAO.delete(commentDAO.findAll().get(0));
        System.out.println("\tComments after removal from comment: " + commentDAO.findAll()); // Comment '1' is removed from comments
        System.out.print("\tPost comments after removal from comment: ");
        postDAO.findAllWithComments().forEach(System.out::println); //Comment '1' is removed also from post
        System.out.println("Removing post");
        postDAO.delete(postDAO.findAll().get(0));
        System.out.println("\tPosts after deletions: " + postDAO.findAllWithComments());
        System.out.println("\tComments after post deletion: " + commentDAO.findAll()); // When we remove a post also its comments are removed
    }
}