package com.graduation.furniture.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    private Integer quantity;

    private Integer paymentOrderItem;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    private boolean deleted;

    private boolean feedbackStatus;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderUser orderUser;

    @ManyToOne
    @JoinColumn(name = "product_id" )
    private Product product;
}
