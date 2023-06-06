package com.graduation.furniture.controller;

import com.graduation.furniture.entities.OrderItem;
import com.graduation.furniture.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderItem>> findItemOfOrder(@PathVariable String id) {
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
        List<OrderItem> orderItems = orderItemService.findOrderItemByOrderUserOrderId(orderId);
        if (orderItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderItems);
        }
    }
}
