package com.graduation.furniture.repository;

import com.graduation.furniture.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Page<Category> findAllByDeleted(boolean deleted, Pageable pageable);

    List<Category> findAllByDeleted(boolean deleted);
}
