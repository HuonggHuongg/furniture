package com.graduation.furniture.dto;

import com.graduation.furniture.entities.OrderUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderItemDTO {

    private Integer orderItemId;

    private Integer quantity;

    private Integer paymentOrderItem;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    private boolean isActivity;

    private OrderUser orderUser;
}
