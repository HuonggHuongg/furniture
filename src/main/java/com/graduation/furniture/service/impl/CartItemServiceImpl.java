package com.graduation.furniture.service.impl;

import com.graduation.furniture.dto.CartItemDTO;
import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Product;
import com.graduation.furniture.repository.CartItemRepo;
import com.graduation.furniture.repository.CartRepo;
import com.graduation.furniture.repository.ProductRepo;
import com.graduation.furniture.repository.UserRepo;
import com.graduation.furniture.service.CartItemService;
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
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public CartItem addProductToCartItem(String username, CartItemDTO cartItemDTO) {
        Cart cart = cartRepo.findByUser_UserName(username);
        LocalDate now = LocalDate.now();
        CartItem cartItem = new CartItem();
        Product product = productRepo.findById(cartItemDTO.getProductId()).orElse(null);
        Integer payment = cartItemDTO.getQuantity() * product.getPrice();
        cartItem.setPaymentCartItem(payment);
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setCreatedAt(now);
        return cartItemRepo.save(cartItem);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        return null;
    }

    @Override
    public void deleteByUserName(String userName) {
        cartItemRepo.deleteByCart_User_UserName(userName);
    }

    @Override
    public Optional<CartItem> findById(Integer id) {
        return cartItemRepo.findById(id);
    }

    @Override
    public List<CartItem> findByUserName(String userName) {
        List<CartItem> cartItems = cartItemRepo.findCartItemByCart_User_UserName(userName);
        cartItems.forEach(cartItem -> {
            System.out.println(cartItem.getProduct().getProductId());
        });
        return cartItemRepo.findCartItemByCart_User_UserName(userName);
    }

    @Override
    public void deleteByProductId(Integer productId) {
        cartItemRepo.deleteByProduct_ProductId(productId);
    }

    @Override
    public List<CartItem> findAll() {
        return cartItemRepo.findAll();
    }

    @Override
    public void deleteById(Integer cartItemId) {
        cartItemRepo.deleteById(cartItemId);
    }


}
