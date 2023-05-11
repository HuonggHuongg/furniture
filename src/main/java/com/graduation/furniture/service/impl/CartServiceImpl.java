package com.graduation.furniture.service.impl;

import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Product;
import com.graduation.furniture.repository.CartRepo;
import com.graduation.furniture.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Override
    public Cart save(Cart cart) {
        LocalDate now = LocalDate.now();
        cart.setCreatedAt(now);
        return cartRepo.save(cart);
    }

    @Override
    public CartItem addProductToCart(Product product, Cart cart) {
        CartItem cartItem = new CartItem();
        LocalDate now = LocalDate.now();
        Integer payment = cartItem.getQuantity() * product.getPrice();
        cartItem.setPaymentCartItem(payment);
        cartItem.setCreatedAt(now);
        cartItem.setProduct(product);
        cartItem.setCart(cart);
//        cart.getCartItems().add(cartItem);
        return cartItem;
    }

    @Override
    public Cart update(Cart cart) {
        Integer total = cartRepo.getTotal(cart.getCartId());
        LocalDateTime now = LocalDateTime.now();
        cart.setUpdatedAt(now);
        cart.setTotal(total);
        return cartRepo.save(cart);
    }

    @Override
    public void deleteById(Integer id) {
        cartRepo.deleteById(id);
    }

    @Override
    public Optional<Cart> findById(Integer id) {
        return cartRepo.findById(id);
    }

    @Override
    public Cart findByUsername(String username) {
        return cartRepo.findByUser_UserName(username);
    }

    @Override
    public Page<Cart> findAll(int pageNum, int size) {
        Pageable pageable = PageRequest.of(pageNum-1, size);
        return cartRepo.findAll(pageable);
    }
}
