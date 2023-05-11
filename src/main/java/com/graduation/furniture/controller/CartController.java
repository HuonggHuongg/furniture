package com.graduation.furniture.controller;

import com.graduation.furniture.dto.CartDTO;
import com.graduation.furniture.dto.CartItemDTO;
import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.CartItem;
import com.graduation.furniture.entities.Product;
import com.graduation.furniture.entities.Users;
import com.graduation.furniture.service.CartItemService;
import com.graduation.furniture.service.CartService;
import com.graduation.furniture.service.ProductService;
import com.graduation.furniture.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Secured("ROLE_USER")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("")
    public ResponseEntity<Page<Cart>> findAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                              @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Cart> cartPage = cartService.findAll(page, size);
        if (cartPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cartPage);
        }
    }

    @PostMapping("/{username}/items")
    public ResponseEntity<?> addProductToCartItem(Authentication currentUser, @PathVariable String username, @RequestBody CartItemDTO cartItemDTO) {
        if (!username.equals(currentUser.getName())) {
            return new ResponseEntity<>("You do not have permission to edit this user's shopping cart information", HttpStatus.UNAUTHORIZED);
        }
        Product product = productService.findById(cartItemDTO.getProductId()).orElse(null);
        if (product != null){
            if(product.getInventoryQuantity() < cartItemDTO.getQuantity()){
                return new ResponseEntity<>("Products in stock are not enough", HttpStatus.BAD_REQUEST);
            }
        }
        Cart cart = cartService.findByUsername(username);
        CartItem cartItem = cartItemService.addProductToCartItem(username, cartItemDTO);
        Cart updateCart = cartService.update(cart);
        return ResponseEntity.ok(cartItem);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CartDTO feedbackDTO, BindingResult bindingResult) {
        Integer idUpdate = null;
        try {
            idUpdate = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        new CartDTO().validate(feedbackDTO, bindingResult);
        Users user = userService.findById(feedbackDTO.getUser().getUserName()).orElse(null);

        Cart feedbackUpdate = cartService.findById(idUpdate).orElse(null);
        if (feedbackUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        Cart feedback = new Cart();
        BeanUtils.copyProperties(feedbackDTO, feedback);
        Cart newFeedback = cartService.update(feedback);
        return ResponseEntity.ok(newFeedback);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> findById(@PathVariable String id) {
        Integer idFind = null;
        try {
            idFind = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        Cart feedback = cartService.findById(idFind).orElse(null);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteById(@PathVariable String id) {

        Integer idDelete = null;
        try {
            idDelete = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        Cart feedback = cartService.findById(idDelete).orElse(null);

        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        cartService.deleteById(idDelete);
        return ResponseEntity.ok(feedback);
    }
}
