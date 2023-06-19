package com.graduation.furniture.service;

import com.graduation.furniture.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService{

    Category save(Category category);

    void deleteById(Integer categoryId);

    Optional<Category> findById(Integer categoryId);

    Page<Category> findAll(int pageNum, int size);

    List<Category> findAll();

    Category findByCategoryName(String categoryName);
}
