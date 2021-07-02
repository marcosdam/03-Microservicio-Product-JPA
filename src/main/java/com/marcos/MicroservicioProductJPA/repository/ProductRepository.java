package com.marcos.MicroservicioProductJPA.repository;

import com.marcos.MicroservicioProductJPA.entity.Category;
import com.marcos.MicroservicioProductJPA.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Query todos los productos de una categoría
    public List<Product> findByCategory(Category category);
}
