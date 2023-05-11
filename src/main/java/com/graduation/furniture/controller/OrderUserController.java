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
@Secured("ROLE_USER")
public class OrderUserController {

    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("")
    public ResponseEntity<Page<OrderUser>> findAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "5") int size){
        Page<OrderUser> orderUserPage = orderUserService.findAll(page, size);
        if(orderUserPage.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(orderUserPage);
        }
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(Authentication currentUser, @RequestBody OrderUserDTO orderUserDTO, BindingResult bindingResult) {
        new OrderUserDTO().validate(orderUserDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        if (!orderUserDTO.getUser().getUserName().equals(currentUser.getName())) {
            return new ResponseEntity<>("You do not have permission to edit this user's shopping cart information", HttpStatus.UNAUTHORIZED);
        }
        OrderUser orderUser = new OrderUser();
        BeanUtils.copyProperties(orderUserDTO, orderUser);
        OrderUser newOrder = orderUserService.save(orderUser);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @PatchMapping(value = "/changeStatus/{id}")
    public ResponseEntity<?> changeStatusOrder(Authentication currentUser, @PathVariable String id, @RequestBody OrderUserDTO orderUserDTO, BindingResult bindingResult ){
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        new OrderUserDTO().validate(orderUserDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        if (!orderUserDTO.getUser().getUserName().equals(currentUser.getName())) {
            return new ResponseEntity<>("You do not have permission to edit this user's shopping cart information", HttpStatus.UNAUTHORIZED);
        }
        OrderUser orderUser = new OrderUser();
        orderUserDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderUserDTO, orderUser);
        OrderUser updateOrder = orderUserService.changeStatusOrder(orderUser);
        return ResponseEntity.ok(updateOrder);
    }

    @PatchMapping(value = "/changeTotal/{id}")
    public ResponseEntity<?> changeTotalOrder(Authentication currentUser, @PathVariable String id, @RequestBody OrderUserDTO orderUserDTO, BindingResult bindingResult ){
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        new OrderUserDTO().validate(orderUserDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        if (!orderUserDTO.getUser().getUserName().equals(currentUser.getName())) {
            return new ResponseEntity<>("You do not have permission to edit this user's shopping cart information", HttpStatus.UNAUTHORIZED);
        }
        OrderUser orderUser = new OrderUser();
        orderUserDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderUserDTO, orderUser);
        OrderUser updateOrder = orderUserService.changeTotalOrder(orderUser);
        return ResponseEntity.ok(updateOrder);
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<?> addProductToOrderItem(Authentication currentUser, @RequestBody OrderUserDTO orderUserDTO) {
        OrderUser orderUser = orderUserService.findById(orderUserDTO.getOrderId()).orElse(null);
        List<CartItem> cartItems = cartItemService.findByUserName(currentUser.getName());
        cartItems.forEach(cartItem -> {
            orderUserDTO.getProductIds().forEach(productId -> {
                if (Objects.equals(cartItem.getProduct().getProductId(), productId)){
                    OrderItem orderItem = new OrderItem();
                    BeanUtils.copyProperties(cartItem, orderItem);
                    orderItem.setPaymentOrderItem(cartItem.getPaymentCartItem());
                    orderItem.setOrderUser(orderUser);
                    orderItemService.save(orderItem);
                    cartItemService.deleteByProductId(productId);
                }
            });
        });
        Cart cart = cartService.findByUsername(currentUser.getName());
        cartService.update(cart);
        return ResponseEntity.ok().build();
    }
}