package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.Category;
import com.graduation.furniture.entities.Product;
import com.graduation.furniture.repository.CategoryRepo;
import com.graduation.furniture.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    private static final boolean DELETED_FALSE = false;

    @Override
    public Category save(Category category) {
        LocalDate  now = LocalDate.now();
        category.setCreatedAt(now);
        return categoryRepo.save(category);
    }

    @Override
    public void deleteById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElse(null);
        if (category != null){
            category.setDeleted(true);
            categoryRepo.save(category);
        }
    }

    @Override
    public Optional<Category> findById(Integer categoryId) {
        return categoryRepo.findById(categoryId);
    }

    @Override
    public Page<Category> findAll(int pageNum, int size) {
        Pageable pageable = PageRequest.of(pageNum-1, size);
        return categoryRepo.findAllByDeleted(DELETED_FALSE ,pageable);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAllByDeleted(DELETED_FALSE);
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }

}
