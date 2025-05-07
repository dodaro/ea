package it.unical.ea.authp.api;

import it.unical.ea.authp.entities.Product;
import it.unical.ea.authp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    // Endpoint pubblico
    @GetMapping("/info")
    public String welcome() {
        return "Welcome! This endpoint is not secured.";
    }

    // Accesso: USER o ADMIN
    @GetMapping("/products")
    public List<Product> getAllTheProducts() {
        return service.getAllProducts();
    }

    // Accesso: solo ADMIN
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProduct(id);
    }
}
