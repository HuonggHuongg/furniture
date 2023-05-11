package com.graduation.furniture.service;

import com.graduation.furniture.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Product update(Product product);

    void deleteById(Integer productId);

    Optional<Product> findById(Integer productId);

    Page<Product> findAll(int pageNum, int size,String search,String filter,String order, String dir);

    List<Product> findAll();
}
