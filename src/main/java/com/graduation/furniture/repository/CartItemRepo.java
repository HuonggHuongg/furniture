package com.graduation.furniture.repository;

import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCart_User_UserName(String userName);

    void deleteByProduct_ProductId(Integer productId);

    void deleteByCart_User_UserName(String userName);
}
