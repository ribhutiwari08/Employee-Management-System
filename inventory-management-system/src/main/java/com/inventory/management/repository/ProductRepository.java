package com.inventory.management.repository;

import com.inventory.management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByQuantityLessThanEqual(Integer quantity);
}
