package com.graduation.furniture.service;

import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart save(Cart cart);

    CartItem addProductToCart(Product product, Cart cart);

    Cart update(Cart cart);

    void deleteById(Integer id);

    Optional<Cart> findById(Integer id);

    Cart findByUsername(String username);

    Page<Cart> findAll(int pageNum, int size);
}
