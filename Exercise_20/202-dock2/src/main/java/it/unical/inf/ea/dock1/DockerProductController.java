package it.unical.inf.ea.dock1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DockerProductController {

    private final ProductDao productDao;

    @GetMapping("/count-products")
    public String getProductsCount() {
        List<Product> products = productDao.findAll();
        return "Products count " + products.size();
    }
}