package it.unical.demacs.informatica.posthandlerservice.repositories;

import it.unical.demacs.informatica.posthandlerservice.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface PostRepository extends CrudRepository<Post, Long> {
    Iterable<Post> findByUsername(String username);
}
