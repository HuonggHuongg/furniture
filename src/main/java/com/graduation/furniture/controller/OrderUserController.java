package com.graduation.furniture.controller;

import com.graduation.furniture.dto.*;
import com.graduation.furniture.entities.*;
import com.graduation.furniture.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderUserController {

    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

//    @GetMapping("")
//    public ResponseEntity<Page<OrderUser>> findAll(@RequestParam(name = "page", defaultValue = "1") int page,
//                                                  @RequestParam(name = "size", defaultValue = "5") int size){
//        Page<OrderUser> orderUserPage = orderUserService.findAll(page, size);
//        if(orderUserPage.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }else {
//            return ResponseEntity.ok(orderUserPage);
//        }
//    }

    @GetMapping("/pending")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<OrderUser>> findOrderUserByStatusPending() {
        List<OrderUser> orderUsers = orderUserService.findOrderUserByStatusPending();
        if (orderUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderUsers);
        }
    }

    @GetMapping("/delivered")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<OrderUser>> findOrderUserByStatusDelivered() {
        List<OrderUser> orderUsers = orderUserService.findOrderUserByStatusDelivered();
        if (orderUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderUsers);
        }
    }

    @GetMapping("")
    @Secured("ROLE_USER")
    public ResponseEntity<List<OrderUser>> findOrderUserByUserUserName(Authentication currentUser) {
        List<OrderUser> orderUsers = orderUserService.findOrderUserByUserUserName(currentUser.getName());
        if (orderUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderUsers);
        }
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(Authentication currentUser, @RequestBody OrderUserDTO orderUserDTO, BindingResult bindingResult) {
        new OrderUserDTO().validate(orderUserDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        Users users = userService.findById(currentUser.getName()).orElse(null);
        OrderUser orderUser = new OrderUser();
        BeanUtils.copyProperties(orderUserDTO, orderUser);
        if (users != null){
            orderUser.setUser(users);
        }
        OrderUser newOrder = orderUserService.save(orderUser);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @PatchMapping(value = "/changeStatus/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> changeStatusOrder(@PathVariable String id) {
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        OrderUser orderUser = orderUserService.findById(orderId).orElse(null);
        if (orderUser == null){
            return ResponseEntity.notFound().build();
        }
        OrderUser updateOrder = orderUserService.changeStatusOrder(orderUser);
        return ResponseEntity.ok(updateOrder);
    }

    @PatchMapping(value = "/changeTotal/{id}")
    public ResponseEntity<?> changeTotalOrder(@PathVariable String id) {
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        OrderUser orderUser = orderUserService.findById(orderId).orElse(null);
        if (orderUser == null){
            return ResponseEntity.notFound().build();
        }
        OrderUser updateOrder = orderUserService.changeTotalOrder(orderUser);
        return ResponseEntity.ok(updateOrder);
    }

    @PatchMapping(value = "/changePaymentStatus")
    public ResponseEntity<?> changePaymentStatus(@RequestParam String id) {
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        OrderUser orderUser = orderUserService.findById(orderId).orElse(null);
        if (orderUser == null){
            return ResponseEntity.notFound().build();
        }
        OrderUser updateOrder = orderUserService.changePaymentStatus(orderId);
        return ResponseEntity.ok(updateOrder);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<?> addProductToOrderItem(@PathVariable String id,Authentication currentUser, @RequestBody OrderUserDTO orderUserDTO) {
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        OrderUser orderUser = orderUserService.findById(orderId).orElse(null);
        List<CartItem> cartItems = cartItemService.findByUserName(currentUser.getName());
        cartItems.forEach(cartItem -> {
            orderUserDTO.getProductIds().forEach(productId -> {
                if (Objects.equals(cartItem.getProduct().getProductId(), productId)) {
                    OrderItem orderItem = new OrderItem();
                    BeanUtils.copyProperties(cartItem, orderItem);
                    orderItem.setPaymentOrderItem(cartItem.getPaymentCartItem());
                    orderItem.setOrderUser(orderUser);
                    orderItemService.save(orderItem);
                    cartItemService.deleteByProductId(productId);
                    Product product = productService.findById(productId).orElse(null);
                    if (product != null){
                        Integer inventory = product.getInventoryQuantity();
                        Integer stock = inventory - orderItem.getQuantity();
                        product.setInventoryQuantity(stock);
                    }
                }
            });
        });
        Cart cart = cartService.findByUsername(currentUser.getName());
        cartService.update(cart);
        return ResponseEntity.ok().build();
    }
}