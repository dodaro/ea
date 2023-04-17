package it.unical.inf.ea.uniprj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utility")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OthersController {

  @GetMapping("/greeting/{user}")
  public ResponseEntity<String> greeting(@PathVariable("user") String user) {
    return ResponseEntity.ok(String.format("Hellooooo %s!!!", user));
  }

  @GetMapping("/greeting")
  public ResponseEntity<String> greeting2() {
    return ResponseEntity.ok(String.format("Hellooooo %s!!!", "a"));
  }

  @GetMapping("/login")
  public ResponseEntity<Boolean> login(@RequestParam("username") String username,
                                       @RequestParam("password") String password ) {
    System.out.println(String.format("Username: [%s] - Password: [%s]", username, password));
    if(username.trim().equals("ciccio") && password.trim().equals("pasticcio"))
      return ResponseEntity.ok(true);
    return ResponseEntity.ok(false);
  }

  @PostMapping("/mocklogin")
  public ResponseEntity<Boolean> otherLogin() {
    return ResponseEntity.ok(true);
  }

}
