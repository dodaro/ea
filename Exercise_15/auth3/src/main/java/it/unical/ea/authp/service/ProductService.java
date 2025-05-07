package it.unical.ea.authp.service;

import it.unical.ea.authp.dao.ProductDao;
import it.unical.ea.authp.entities.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public Product getProduct(long id) {
        return productDao.findById(id).orElseThrow(()->new RuntimeException("product not found"));
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

}
