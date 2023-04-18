package it.unical.demacs.informatica.posthandlerservice.controller;

import it.unical.demacs.informatica.posthandlerservice.model.Post;
import it.unical.demacs.informatica.posthandlerservice.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:{server.port}")
public class PostController {

    private final PostRepository postRepository;
    private final KafkaTemplate<String, String> template;

    public PostController(PostRepository postRepository, KafkaTemplate<String, String> template) {
        this.postRepository = postRepository;
        this.template = template;
    }

    @PostMapping(path="/posts/{username}")
    public ResponseEntity<String> postMessage(
            @PathVariable("username") String username,
            @RequestBody String content) {
        Post post = new Post(username, content);
        postRepository.save(post);
        template.send("post", "New post by " + username + ": " + content);
        return new ResponseEntity<>("added post", HttpStatus.OK);
    }

    @GetMapping(path="/posts/{username}")
    public Iterable<Post> getPosts(@PathVariable("username") String username) {
        return postRepository.findByUsername(username);
    }
}
