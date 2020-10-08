package com.projet.mscatalog.repository;

import com.projet.mscatalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAllByCategory(String category);
    public List<Product> findAllByProductName(String name);

}
