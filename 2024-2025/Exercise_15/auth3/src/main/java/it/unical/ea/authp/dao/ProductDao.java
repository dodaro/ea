package it.unical.ea.authp.dao;

import it.unical.ea.authp.entities.Product;
import it.unical.ea.authp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Long> {
}
