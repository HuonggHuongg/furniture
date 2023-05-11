package com.graduation.furniture.controller;

import com.graduation.furniture.dto.CartItemDTO;
import com.graduation.furniture.dto.CategoryDTO;
import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.service.CartItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/item")
    public ResponseEntity<List<CartItem>> findItemOfCart(Authentication currentUser, @RequestParam(name = "page", defaultValue = "1") int page,
                                                          @RequestParam(name = "size", defaultValue = "5") int size) {
        List<CartItem> cartItems = cartItemService.findByUserName(currentUser.getName());
        if (cartItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cartItems);
        }
    }
}
