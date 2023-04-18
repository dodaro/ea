package it.unical.demacs.informatica.applicationgateway.controller;

import it.unical.demacs.informatica.applicationgateway.services.PostService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:{server.port}")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(path="/posts/{username}")
    public Mono<String> postMessage(
            @PathVariable("username") String username,
            @RequestBody String content) {
        //Here validate parameters!
        return postService.createPost(username, content);
    }

}
