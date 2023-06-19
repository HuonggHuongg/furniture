package com.graduation.furniture.controller;

import com.graduation.furniture.entities.OrderItem;
import com.graduation.furniture.entities.OrderUser;
import com.graduation.furniture.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findItemOfOrder(Authentication currentUser, @PathVariable String id) {
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        boolean isUnAuthorization = false;
        List<OrderItem> orderItems = orderItemService.findOrderItemByOrderUserOrderId(orderId);
        String role = "";
        if(!currentUser.getAuthorities().stream().toList().isEmpty()){
            role = String.valueOf(currentUser.getAuthorities().stream().toList().get(0));
        }
        
  
        if (!orderItems.isEmpty() && !"ROLE_ADMIN".equals(role)) {
            List<OrderItem> invalidOrderItemList = orderItems.stream().filter(orderItem -> !orderItem.getOrderUser()
                    .getUser().getUserName().equals(currentUser.getName())).toList();
            if (!invalidOrderItemList.isEmpty()){
                return new ResponseEntity<>("You do not have permission", HttpStatus.UNAUTHORIZED);
            }
        }
        if (orderItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderItems);
        }
    }

    @PatchMapping(value = "/changeFeedbackStatus")
    public ResponseEntity<?> changeFeedbackStatus(@RequestParam String id) {
        Integer orderItemId = null;
        try {
            orderItemId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        OrderItem orderItem = orderItemService.findById(orderItemId).orElse(null);
        if (orderItem == null) {
            return ResponseEntity.notFound().build();
        }
        OrderItem updateOrderItem = orderItemService.changeFeedbackStatus(orderItemId);
        return ResponseEntity.ok(updateOrderItem);
    }
}
