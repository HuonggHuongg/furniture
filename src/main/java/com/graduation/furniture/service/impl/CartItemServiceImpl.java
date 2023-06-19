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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        LocalDateTime nowDateTime = LocalDateTime.now();

        Product product = productRepo.findById(cartItemDTO.getProductId()).orElse(null);
        List<CartItem> cartItemList = cartItemRepo.findCartItemByCart_User_UserName(username);
        List<CartItem> filterList = cartItemList.stream().filter(item -> item.getProduct().getProductId().equals(cartItemDTO.getProductId())).toList();
        if (!filterList.isEmpty()) {
            filterList.forEach(item -> {
                System.err.println("CÓ ");
                CartItem cartItem = new CartItem();
                System.err.println(" TRÙNG ");
                Integer quantityUpdate = cartItemDTO.getQuantity() + item.getQuantity();
                System.err.println(quantityUpdate);
                Integer payment = quantityUpdate * product.getPrice();
                BeanUtils.copyProperties(item, cartItem);
                cartItem.setPaymentCartItem(payment);
                cartItem.setQuantity(quantityUpdate);
                cartItem.setUpdatedAt(nowDateTime);
                System.err.println(cartItem.getCartItemId());
                cartItemRepo.save(cartItem);
            });
        } else {
            CartItem cartItem = new CartItem();
            System.err.println("CHƯA CÓ ");
            Integer payment = cartItemDTO.getQuantity() * product.getPrice();
            cartItem.setPaymentCartItem(payment);
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItem.setCreatedAt(now);
            System.err.println("SAVE 86");
            cartItemRepo.save(cartItem);
        }
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

    @Override
    public CartItem save(CartItem cartItem) {
        cartItem.setUpdatedAt(LocalDateTime.now());
        return cartItemRepo.save(cartItem);
    }
}
