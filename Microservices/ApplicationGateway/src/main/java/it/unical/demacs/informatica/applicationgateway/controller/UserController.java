package it.unical.demacs.informatica.applicationgateway.controller;

import it.unical.demacs.informatica.applicationgateway.services.PostService;
import it.unical.demacs.informatica.applicationgateway.services.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1", produces = "application/json")
@CrossOrigin(origins = "http://localhost:{server.port}")
public class UserController {

    private final UserService userService;
    private final PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping(path="/register")
    public Mono<String> register(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("consent") boolean consent) {
        //Here validate parameters!
        return userService.register(username, email, consent);
    }

    @GetMapping(path="/users/{username}")
    public Mono<String> getUser(@PathVariable("username") String username) {
        //Here validate parameters!
        Mono<Optional<String>> userDetails = userService.findUserDetails(username)
                .map(Optional::of)
                .onErrorReturn(Optional.empty());

        Mono<String> userPosts = postService.findAllPosts(username);

        Mono<Tuple2<Optional<String>, String>> result = Mono.zip(userDetails, userPosts);
        return result.map(element -> {
            JSONObject response = new JSONObject();
            element.getT1().ifPresent(x -> response.put("user_details", new JSONObject(x)));
            response.put("user_posts", new JSONArray(element.getT2()));
            return response.toString();
        });
    }

}
