package com.graduation.furniture.service;

import com.graduation.furniture.dto.CartItemDTO;
import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    CartItem addProductToCartItem(String username, CartItemDTO cartItemDTO);

    CartItem update(CartItem cartItem);

    void deleteByUserName(String userName);

    Optional<CartItem> findById(Integer id);

    List<CartItem> findByUserName(String userName);

    void deleteByProductId(Integer productId);
    List<CartItem> findAll();

    void deleteById(Integer cartItemId);
}
