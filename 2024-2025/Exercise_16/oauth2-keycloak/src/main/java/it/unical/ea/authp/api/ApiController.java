package it.unical.ea.authp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome this endpoint is for admin user";
    }

    @GetMapping("/basic")
    public String basic() {
        return "Welcome this endpoint is for basic user";
    }
}
