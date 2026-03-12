package it.unical.ea.authp.api;


import it.unical.ea.authp.dto.Product;
import it.unical.ea.authp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')") //con hasRole non bisogna mettere la radice ROLE
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')") //con hasAuthority bisogna mettere la radice ROLE
    public Product getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }
}
