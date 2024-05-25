package it.unical.inf.ea.auth.controller;

import it.unical.inf.ea.auth.dto.Product;
import it.unical.inf.ea.auth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/products")
//@CrossOrigin(origins = "http://example.com", maxAge = 3600)
public class ProductController {

  @Autowired
  private ProductService service;

//  @CrossOrigin("http://example.com")
  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome this endpoint is not secure";
  }

  @GetMapping("/all")
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
