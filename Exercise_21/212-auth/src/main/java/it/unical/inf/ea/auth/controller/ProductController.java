package it.unical.inf.ea.auth.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.unical.inf.ea.auth.dto.Product;
import it.unical.inf.ea.auth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/products")
//@SecurityRequirement(name = "bearerAuth")
//@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
public class ProductController {

  @Autowired
  private ProductService service;

  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome this endpoint is not secure";
  }

  @GetMapping("/all")
  @SecurityRequirement(name = "Bearer Authentication")
  public List<Product> getAllTheProducts() {
    return service.getProducts();
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable int id) {
    return service.getProduct(id);
  }

  @GetMapping("/random")
  public Product getRandomProduct() {
    List<Product> products = service.getProducts();
    return products.get(RandomGenerator.getDefault().nextInt(products.size() - 1));
  }
}
