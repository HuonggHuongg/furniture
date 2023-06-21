package com.graduation.furniture.repository;

import com.graduation.furniture.entities.Category;
import com.graduation.furniture.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory_CategoryId(Integer categoryId);
    @Query("select p from Product p where p.productName like %:productName% " +
            "AND p.category.categoryName = :categoryName " +
            "AND p.deleted = :deleted ")
    Page<Product> getAllProductAndFilter(@Param("productName") String productName,
                                         @Param("categoryName") String categoryName,
                                         @Param("deleted") boolean deleted, Pageable pageable);

    @Query("select p from Product p where p.productName like %:productName% AND p.deleted = :deleted ")
    Page<Product> getAllProduct(@Param("productName") String productName,
                                @Param("deleted") boolean deleted, Pageable pageable);

}
