package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.Category;
import com.graduation.furniture.entities.Product;
import com.graduation.furniture.repository.ProductRepo;
import com.graduation.furniture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    private static final boolean DELETED_FALSE = false;

    @Override
    public Product save(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepo.save(product);
    }

    @Override
    public Product update(Product product) {
        LocalDateTime now = LocalDateTime.now();
        product.setUpdatedAt(now);
        return productRepo.save(product);
    }

    @Override
    public void deleteById(Integer productId) {
        Product product = productRepo.findById(productId).orElse(null);
        if (product != null) {
            product.setDeleted(true);
            productRepo.save(product);
        }
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return productRepo.findById(productId);
    }

    @Override
    public Page<Product> findAll(int pageNum, int size, String search, String filter, String order, String dir) {
        Pageable pageable;

        if ("".equals(order)) {
            pageable = PageRequest.of(pageNum - 1, size, Sort.by("createdAt").descending());
        } else {
            if (!"desc".equals(dir)) {
                pageable = PageRequest.of(pageNum - 1, size, Sort.by(order));
            } else {
                pageable = PageRequest.of(pageNum - 1, size, Sort.by(order).descending());
            }
        }
        if ("".equals(filter)) {
            return productRepo.getAllProduct(search, DELETED_FALSE, pageable);
        }
        return productRepo.getAllProductAndFilter(search, filter, DELETED_FALSE, pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> findProductByCategoryId(Integer categoryId) {
        return productRepo.findAllByCategory_CategoryId(categoryId);
    }
}
